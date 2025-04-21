package com.example.chronobooks.data;

public class AuthResponse {
    // Field for storing the authentication token returned from the backend
    private String token;

    // Field for storing a message, usually an error or status message
    private String message;

    // Getter method for retrieving the authentication token
    public String getToken() {
        return token;
    }

    // Getter method for retrieving the message
    public String getMessage() {
        return message;
    }
}
