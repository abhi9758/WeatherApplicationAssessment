// Package declaration
package com.assessment.WeatherApp.model;

public class JwtResponse {

    // Private fields for username and JWT token
    private String username;
    private String jwtToken;

    // Private constructor to enforce the use of the builder
    private JwtResponse(String username, String jwtToken) {
        this.username = username;
        this.jwtToken = jwtToken;
    }

    // Builder class for constructing JwtResponse instances
    public static class Builder {

        // Fields for the builder
        private static String username;
        private static String jwtToken;

        // Setter method for username
        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        // Setter method for JWT token
        public Builder withJwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        // Build method to create a new JwtResponse instance
        public static JwtResponse build() {
            // Validate if necessary
            // For simplicity, let's assume both username and jwtToken are required

            return new JwtResponse(username, jwtToken);
        }
    }

    // Getter method for the JWT token
    public String getJwtToken() {
        return jwtToken;
    }

    // Getter method for the username
    public String getUsername() {
        return username;
    }
}
