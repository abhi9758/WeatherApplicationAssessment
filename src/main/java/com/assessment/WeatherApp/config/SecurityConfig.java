// Package and imports for the class
package com.assessment.WeatherApp.config;

import com.assessment.WeatherApp.Authorization.JwtAuthenticationEntryPoint;
import com.assessment.WeatherApp.Authorization.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Configuration class for security-related settings
@Configuration
public class SecurityConfig {

    // Autowired JwtAuthenticationEntryPoint and JwtAuthenticationFilter
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    // Bean definition for SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuring HttpSecurity for various security settings

        // Disabling CSRF protection
        http.csrf(csrf -> csrf.disable())
                // Configuring authorization rules
                .authorizeRequests()
                // Requests to "/test" require authentication
                .requestMatchers("/test").authenticated()
                // Requests to "/auth/login" are permitted without authentication
                .requestMatchers("/auth/login").permitAll()
                // Any other request requires authentication
                .anyRequest().authenticated()
                .and()
                // Configuring exception handling, setting the authentication entry point
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                // Configuring session management to be STATELESS, as this is a stateless application
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Adding JwtAuthenticationFilter before the default UsernamePasswordAuthenticationFilter
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        // Building and returning the SecurityFilterChain
        return http.build();
    }
}
