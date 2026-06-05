package com.example.test.config;

import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    private final JwtFilter jwtFilter;
//    @Bean
//    public UserDetailsService users() {
//        UserDetails flight_owner = User.builder()
//                .username("owner_indigo")
//                .password("{noop}pass123")
//                .roles("FLIGHT_OWNER")
//                .build();
//        UserDetails passenger = User.builder()
//                .username("passenger_priya")
//                .password("{noop}pas456")
//                .roles("PASSENGER")
//                .build();
//        return new InMemoryUserDetailsManager(flight_owner, passenger);
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/employer/add-job").hasAuthority("EMPLOYER")
                        .requestMatchers(HttpMethod.GET,"/api/job/get-all").hasAnyAuthority("SEEKER","EMPLOYER")
                        .requestMatchers(HttpMethod.POST,"/api/application/add").hasAuthority("SEEKER")
                        .requestMatchers(HttpMethod.GET,"//api/jobseeker/my-application").hasAuthority("SEEKER")
                        .anyRequest().authenticated()

                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider(userService);
        daoAuthenticationProvider.setPasswordEncoder(getEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }


}


