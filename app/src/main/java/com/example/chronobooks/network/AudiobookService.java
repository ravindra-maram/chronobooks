package com.example.chronobooks.network; // path: app/src/main/java/com/example/chronobooks/network/AudiobookService.java

import com.example.chronobooks.data.Audiobook;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface AudiobookService {
    // Endpoint for searching audiobooks from the backend API
    @GET("api/audiobooks")
    Call<List<Audiobook>> searchAudiobooks(
            // Query parameter for search term (e.g., audiobook title, author, etc.)
            @Query("q") String query,

            // Authorization header to include the JWT token for authenticated requests
            @Header("Authorization") String token
    );
}
