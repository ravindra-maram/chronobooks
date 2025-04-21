package com.example.chronobooks.repository; // path: app/src/main/java/com/example/chronobooks/repository/AudiobookRepository.java

import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.AudiobookService;

import java.util.List;
import retrofit2.Call;

public class AudiobookRepository {
    // Service for making API calls to the audiobook-related endpoints
    private final AudiobookService service;

    // Constructor to initialize the repository and set up the service
    public AudiobookRepository() {
        // Initialize the service by creating an instance of AudiobookService from Retrofit
        service = ApiClient.getRetrofitInstance().create(AudiobookService.class);
    }

    // Method to search for audiobooks by query and token
    public Call<List<Audiobook>> searchAudiobooks(String query, String token) {
        // Make the API call to search audiobooks, passing the query and authorization token
        return service.searchAudiobooks(query, "Bearer " + token);
    }
}
