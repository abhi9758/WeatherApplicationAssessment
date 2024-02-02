// Package declaration
package com.assessment.WeatherApp.model;

// Model class representing a User
public class User {

    // Fields for user details
    private String userId;
    private String name;
    private String email;

    // Constructor for initializing User with userId, name, and email
    public User(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getter method for retrieving userId
    public String getUserId() {
        return userId;
    }

    // Setter method for setting userId
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter method for retrieving name
    public String getName() {
        return name;
    }

    // Setter method for setting name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for retrieving email
    public String getEmail() {
        return email;
    }

    // Setter method for setting email
    public void setEmail(String email) {
        this.email = email;
    }
}
