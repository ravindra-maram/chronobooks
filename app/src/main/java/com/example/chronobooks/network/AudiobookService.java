package com.example.chronobooks.network;

import com.example.chronobooks.data.Audiobook;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Header;

public interface AudiobookService {

    @GET("audiobooks")
    Call<List<Audiobook>> getAllAudiobooks(@Header("Authorization") String token);

    @GET("audiobooks/{id}")
    Call<Audiobook> getAudiobookById(@Path("id") String id, @Header("Authorization") String token);
}
