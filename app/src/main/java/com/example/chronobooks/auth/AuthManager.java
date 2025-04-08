package com.example.chronobooks.auth;

public class AuthManager {

    // For now, simulate token login
    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("1234");
    }

    // In future: use Retrofit call to server here
}
