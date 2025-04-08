package com.example.chronobooks.data;

import java.util.List;

public class Audiobook {
    private String id;
    private String title;
    private String author;
    private String coverUrl;
    private List<Chapter> chapters;

    public Audiobook(String id, String title, String author, String coverUrl, List<Chapter> chapters) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
        this.chapters = chapters;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCoverUrl() { return coverUrl; }
    public List<Chapter> getChapters() { return chapters; }
}
