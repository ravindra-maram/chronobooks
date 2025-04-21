package com.example.chronobooks.network;

import com.example.chronobooks.data.AuthRequest;
import com.example.chronobooks.data.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    // POST request for user registration (endpoint: /api/users/register)
    @POST("users/register")   // ✅ /api/users/register
    Call<AuthResponse> register(@Body AuthRequest request);

    // POST request for user login (endpoint: /api/users/login)
    @POST("users/login")      // ✅ /api/users/login
    Call<AuthResponse> login(@Body AuthRequest request);
}
