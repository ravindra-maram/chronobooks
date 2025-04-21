package com.example.chronobooks.data;

import java.io.Serializable;

public class Audiobook implements Serializable {
    // Fields for storing audiobook details
    private String title;  // Title of the audiobook
    private String author; // Author of the audiobook
    private String audioUrl; // URL to the audio file
    private String coverUrl; // URL to the cover image

    // Default constructor
    public Audiobook() {
    }

    // Parameterized constructor to initialize an Audiobook object
    public Audiobook(String title, String author, String audioUrl, String coverUrl) {
        this.title = title;
        this.author = author;
        this.audioUrl = audioUrl;
        this.coverUrl = coverUrl;
    }

    // Getter method for the title
    public String getTitle() {
        return title;
    }

    // Getter method for the author
    public String getAuthor() {
        return author;
    }

    // Getter method for the audio URL
    public String getAudioUrl() {
        return audioUrl;
    }

    // Getter method for the cover URL
    public String getCoverUrl() {
        return coverUrl;
    }

    // Setter method for the title
    public void setTitle(String title) {
        this.title = title;
    }

    // Setter method for the author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Setter method for the audio URL
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    // Setter method for the cover URL
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
