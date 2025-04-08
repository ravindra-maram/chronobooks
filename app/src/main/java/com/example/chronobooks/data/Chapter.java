package com.example.chronobooks.data;

public class Chapter {
    private String title;
    private int duration; // in seconds
    private int order;

    public Chapter(String title, int duration, int order) {
        this.title = title;
        this.duration = duration;
        this.order = order;
    }

    // Getters
    public String getTitle() { return title; }
    public int getDuration() { return duration; }
    public int getOrder() { return order; }
}
