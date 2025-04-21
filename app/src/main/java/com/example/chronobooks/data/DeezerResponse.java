package com.example.chronobooks.data;

import java.util.List;

public class DeezerResponse {
    // Field for storing a list of Track objects, representing the data returned from Deezer API
    private List<Track> data;

    // Getter method for retrieving the list of tracks
    public List<Track> getData() {
        return data;
    }
}
