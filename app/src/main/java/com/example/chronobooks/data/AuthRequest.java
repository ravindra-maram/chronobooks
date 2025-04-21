package com.example.chronobooks.data;

public class AuthRequest {
    // Field for storing the user's email (must match backend expected field)
    private String email;     // ✅ This must match backend

    // Field for storing the user's password
    private String password;

    // Constructor to initialize AuthRequest with email and password
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
