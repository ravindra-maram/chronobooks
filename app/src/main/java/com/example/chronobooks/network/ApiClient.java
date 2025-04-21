package com.example.chronobooks.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    // URL for the local backend server (replace with your backend URL)
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // ✅ Your local backend

    // URL for the Deezer API
    private static final String DEEZER_URL = "https://api.deezer.com/";

    // Static Retrofit instances for the local backend and Deezer API
    private static Retrofit localRetrofit = null;
    private static Retrofit deezerRetrofit = null;

    // Method to get a Retrofit instance for the local backend
    public static Retrofit getRetrofitInstance() {
        if (localRetrofit == null) {
            // Initialize the Retrofit instance with the BASE_URL and Gson converter
            localRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return localRetrofit;
    }

    // Method to get a Retrofit instance for the Deezer API
    public static Retrofit getDeezerClient() {
        if (deezerRetrofit == null) {
            // Initialize the Retrofit instance with the Deezer URL and Gson converter
            deezerRetrofit = new Retrofit.Builder()
                    .baseUrl(DEEZER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return deezerRetrofit;
    }
}
