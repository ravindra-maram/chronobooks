package com.example.chronobooks.repository;

import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.AudiobookService;

import java.util.List;

import retrofit2.Call;

public class AudiobookRepository {

    private final AudiobookService audiobookService;

    public AudiobookRepository() {
        audiobookService = ApiClient.getInstance().create(AudiobookService.class);
    }

    public Call<List<Audiobook>> getAllAudiobooks(String token) {
        return audiobookService.getAllAudiobooks("Bearer " + token);
    }

    public Call<Audiobook> getAudiobookById(String id, String token) {
        return audiobookService.getAudiobookById(id, "Bearer " + token);
    }
}
