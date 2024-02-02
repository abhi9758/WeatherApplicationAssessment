// Package and imports for the class
package com.assessment.WeatherApp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// Configuration class for security-related beans
@Configuration
class MyConfig {

    // Bean definition for UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        // Creating a UserDetails object for an in-memory user with the username "Abhishek", encrypted password, and the role "ADMIN"
        UserDetails userDetails = User.builder()
                .username("Abhishek")
                .password(passwordEncoder().encode("Abhi@12345"))
                .roles("ADMIN")
                .build();
        // Returning an InMemoryUserDetailsManager with the created UserDetails
        return new InMemoryUserDetailsManager(userDetails);
    }

    // Bean definition for PasswordEncoder, using BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean definition for AuthenticationManager, obtaining it from AuthenticationConfiguration
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
