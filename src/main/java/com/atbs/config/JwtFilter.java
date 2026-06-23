package com.atbs.config;

import com.atbs.model.User;
import com.atbs.service.UserService;
import com.atbs.utility.JwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizedHeader=request.getHeader("Authorization");
        String username=null;
        String jwt=null;

        try{
            if(authorizedHeader!=null && authorizedHeader.startsWith("Bearer ")){
                jwt=authorizedHeader.substring(7);
                username=jwtUtility.extractUsername(jwt);
            }
            if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){

                User user=(User) userService.loadUserByUsername(username);

                boolean status = jwtUtility.validateToken(jwt, user.getUsername());
                if(status){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
        catch (Exception e){
            throw new RuntimeException("Token is not found");
        }
    }
}
