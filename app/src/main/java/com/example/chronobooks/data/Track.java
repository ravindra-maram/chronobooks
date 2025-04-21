package com.example.chronobooks.data;

public class Track {
    // Field for storing the title of the track
    private String title;

    // Field for storing the artist associated with the track
    private Artist artist;

    // Field for storing the preview URL of the track (could be a short audio clip)
    private String preview;

    // Field for storing the album associated with the track
    private Album album;

    // Getter method for retrieving the title of the track
    public String getTitle() {
        return title;
    }

    // Getter method for retrieving the artist of the track
    public Artist getArtist() {
        return artist;
    }

    // Getter method for retrieving the preview URL of the track
    public String getPreview() {
        return preview;
    }

    // Getter method for retrieving the album of the track
    public Album getAlbum() {
        return album;
    }
}
