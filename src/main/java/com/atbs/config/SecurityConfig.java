package com.atbs.config;

import com.atbs.service.UserService;
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
    private static final String ADMIN = "ADMIN";
    private static final String AIRLINES = "FLIGHT_OWNER";
    private static final String PASSENGER = "PASSENGER";



    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http

                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/flight/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/flight/all/v2").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.POST,"/api/auth/admin/add").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.POST,"/api/auth/passenger/add").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/admin/stat").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/flight-owner/stat").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/flight/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/booking/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/auth/flight-owner/add").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/auth/login").authenticated()
                            .requestMatchers(HttpMethod.GET,"/api/passenger/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/passenger/getby-Id/{id}").hasAnyAuthority(ADMIN,PASSENGER)
                            .requestMatchers(HttpMethod.DELETE,"/api/passenger/delete/{id}").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.POST,"/api/passenger/id/upload").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/passenger/profile").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/flight-owner/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/flight-owner/profile").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/flight-owner/getby-Id/{id}").hasAnyAuthority(ADMIN,AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/flight/getby-Id/{id}").hasAnyAuthority(ADMIN,AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/flight/add").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.DELETE,"/api/flight/delete/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/route/all/active").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/route/all").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/route/getBy-Id/{id}").authenticated()
                            .requestMatchers(HttpMethod.GET,"/api/route/search").permitAll()
                            .requestMatchers(HttpMethod.POST,"/api/route/add").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/schedule/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/schedule/getBy-Id/{id}").authenticated()
                            .requestMatchers(HttpMethod.GET,"/api/booking/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/booking/getByPassenger-Id/{id}").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.POST,"/api/booking/addBooking").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/schedule/search").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/booking/schedule/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/payment/add/{bookingId}").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/booking/history").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/booking/ticket").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/generate/ticket/{bookingId}").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/payment/booking/{bookingId}").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/payment/{paymentId}").hasAnyAuthority(PASSENGER,ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/payment/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/payment/all/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/cancellation/add/{bookingId}").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/cancellation/all").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/cancellation/passenger").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/cancellation/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/cancellation/request/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/cancellation/{cancellationId}").hasAnyAuthority(ADMIN,PASSENGER)
                            .requestMatchers(HttpMethod.PUT,"/api/cancellation/approve/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.PUT,"/api/cancellation/reject/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/schedule/add").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/schedule/all/flight-owner").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.PUT,"/api/schedule/delay/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.PUT,"/api/schedule/cancel/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.PUT,"/api/schedule/active/{id}").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/schedule/getBy-Id/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/seat/available/{scheduleId}").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/booking/get-by/{id}").authenticated()
                            .requestMatchers(HttpMethod.GET,"/api/admin/stat/bookings-by-flight-owner").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.GET,"/api/flight-owner/stat/schedule-status").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.GET,"/api/passenger/stat/booking-status").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.GET,"/api/passenger/stat").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.POST,"/api/flight-owner/reset/password").hasAuthority(AIRLINES)
                            .requestMatchers(HttpMethod.POST,"/api/passeneger/reset/password").hasAuthority(PASSENGER)
                            .requestMatchers(HttpMethod.PUT,"/api/route/active/{id}").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.PUT,"/api/route/inactive/{id}").hasAuthority(ADMIN)
                            .requestMatchers(HttpMethod.PUT,"/api/route/discontinue/{id}").hasAuthority(ADMIN)






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

