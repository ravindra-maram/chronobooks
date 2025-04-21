package com.example.chronobooks.data;

public class User {
    // Field for storing the username of the user
    private String username;

    // Field for storing the authentication token associated with the user
    private String token;

    // Constructor to initialize a User object with username and token
    public User(String username, String token) {
        this.username = username;
        this.token = token;
    }

    // Getter method for retrieving the username
    public String getUsername() {
        return username;
    }

    // Getter method for retrieving the authentication token
    public String getToken() {
        return token;
    }
}
