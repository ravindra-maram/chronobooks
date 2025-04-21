package com.example.chronobooks.data;

import com.google.gson.annotations.SerializedName;

public class Album {
    // Field for storing the URL of the medium-sized album cover image
    @SerializedName("cover_medium")
    private String coverMedium;

    // Getter method to retrieve the medium-sized album cover URL
    public String getCoverMedium() {
        return coverMedium;
    }
}
