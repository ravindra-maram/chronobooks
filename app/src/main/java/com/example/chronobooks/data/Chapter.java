package com.example.chronobooks.data;

public class Chapter {
    // Field for storing the title of the chapter
    private String title;

    // Field for storing the start time of the chapter in milliseconds
    private long startTimeMillis;

    // Constructor to initialize a Chapter object with title and start time
    public Chapter(String title, long startTimeMillis) {
        this.title = title;
        this.startTimeMillis = startTimeMillis;
    }

    // Getter method for retrieving the title of the chapter
    public String getTitle() {
        return title;
    }

    // Getter method for retrieving the start time of the chapter in milliseconds
    public long getStartTimeMillis() {
        return startTimeMillis;
    }
}
