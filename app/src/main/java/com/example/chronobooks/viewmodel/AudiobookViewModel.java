package com.example.chronobooks.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.data.DeezerResponse;
import com.example.chronobooks.data.Track;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.DeezerService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudiobookViewModel extends ViewModel {

    // MutableLiveData to store the list of audiobooks
    private final MutableLiveData<List<Audiobook>> audiobooks = new MutableLiveData<>();

    // Getter method to access the audiobooks data
    public MutableLiveData<List<Audiobook>> getAudiobooks() {
        return audiobooks;
    }

    // Method to fetch audiobooks from the Deezer API based on a query and authorization token
    public void fetchAudiobooks(String token, String query) {
        // Create a DeezerService instance to make the API call
        DeezerService service = ApiClient.getDeezerClient().create(DeezerService.class);

        // Call the searchTracks method with a valid query (fallback to "audiobooks" if query is empty)
        service.searchTracks(query != null && !query.isEmpty() ? query : "audiobooks")
                .enqueue(new Callback<DeezerResponse>() {
                    @Override
                    public void onResponse(Call<DeezerResponse> call, Response<DeezerResponse> response) {
                        // On successful response, parse the data
                        if (response.isSuccessful() && response.body() != null && response.body().getData() != null) {
                            // Create a list to hold the parsed Audiobook objects
                            List<Audiobook> result = new ArrayList<>();

                            // Loop through the response data (tracks) and create Audiobook objects
                            for (Track track : response.body().getData()) {
                                result.add(new Audiobook(
                                        track.getTitle(),  // Title of the audiobook
                                        track.getArtist().getName(),  // Artist of the audiobook
                                        track.getPreview(),  // Preview URL of the audiobook
                                        track.getAlbum().getCoverMedium()  // Cover image URL of the album
                                ));
                            }

                            // Set the list of audiobooks in the MutableLiveData
                            audiobooks.setValue(result);
                        } else {
                            // In case of empty or null data, set an empty list
                            audiobooks.setValue(new ArrayList<>());
                        }
                    }

                    @Override
                    public void onFailure(Call<DeezerResponse> call, Throwable t) {
                        // On failure (e.g., network error), set the MutableLiveData to null
                        audiobooks.setValue(null);
                    }
                });
    }
}
