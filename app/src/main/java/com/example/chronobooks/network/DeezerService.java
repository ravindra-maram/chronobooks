package com.example.chronobooks.network;

import com.example.chronobooks.data.DeezerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DeezerService {
    // GET request to search for tracks from Deezer API
    @GET("search")
    Call<DeezerResponse> searchTracks(
            // Query parameter for the search term (e.g., track name, artist)
            @Query("q") String query
    );
}
