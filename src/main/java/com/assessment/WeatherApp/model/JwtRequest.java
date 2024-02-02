// Package declaration
package com.assessment.WeatherApp.model;


public class JwtRequest {

    // Fields for email and password
    private String email;
    private String password;

    // Constructors
    public JwtRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Default constructor
    public JwtRequest() {}

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}
