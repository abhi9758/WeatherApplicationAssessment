// Package and imports for the class
package com.assessment.WeatherApp.controller;

import com.assessment.WeatherApp.Authorization.JwtHelper;
import com.assessment.WeatherApp.model.JwtRequest;
import com.assessment.WeatherApp.model.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

// RestController for handling authentication-related requests
@RestController
@RequestMapping("/auth")
public class AuthController {

    // Autowired UserDetailsService, AuthenticationManager, and JwtHelper
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;

    // Logger for logging messages
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // POST endpoint for handling login requests
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        // Authenticate the user with provided credentials
        this.doAuthenticate(request.getEmail(), request.getPassword());

        // Load user details and generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        // Build the JwtResponse object with the generated token and user details
        JwtResponse response = new JwtResponse.Builder()
                .withJwtToken(token)
                .withUsername(userDetails.getUsername())
                .build();

        // Return the JwtResponse in the ResponseEntity with HTTP status OK
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Private method to perform authentication using Spring Security's AuthenticationManager
    private void doAuthenticate(String email, String password) {
        // Create an authentication token with the provided username and password
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            // Attempt authentication using the AuthenticationManager
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            // Throw a BadCredentialsException if authentication fails
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    // Exception handler for handling BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        // Return a custom message when BadCredentialsException is caught
        return "Credentials Invalid !!";
    }
}
