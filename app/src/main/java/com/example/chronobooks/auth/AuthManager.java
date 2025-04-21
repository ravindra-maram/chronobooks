package com.example.chronobooks.auth;

import android.content.Context;
import android.content.SharedPreferences;

public class AuthManager {
    // SharedPreferences file name for authentication
    private static final String PREFS_NAME = "auth_prefs";

    // Key for storing the authentication token
    private static final String KEY_TOKEN = "auth_token";

    // Method to save the authentication token to SharedPreferences
    public static void setToken(Context context, String token) {
        // Get the SharedPreferences editor
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Store the token in SharedPreferences
        prefs.edit().putString(KEY_TOKEN, token).apply();
    }

    // Method to retrieve the authentication token from SharedPreferences
    public static String getToken(Context context) {
        // Get the SharedPreferences instance
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Return the stored token or null if not found
        return prefs.getString(KEY_TOKEN, null);
    }

    // Method to check if the user is logged in by verifying the presence of a token
    public static boolean isLoggedIn(Context context) {
        // Get the token from SharedPreferences
        String token = getToken(context);

        // Return true if token is not null or empty, else false
        return token != null && !token.isEmpty();
    }

    // Method to log out by removing the stored token from SharedPreferences
    public static void logout(Context context) {
        // Get the SharedPreferences editor
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Remove the stored token from SharedPreferences
        prefs.edit().remove(KEY_TOKEN).apply();
    }
}
