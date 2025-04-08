package com.example.chronobooks.utils;

public class TimeUtils {

    public static String formatDuration(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public static String formatTimeVerbose(int seconds) {
        int minutes = seconds / 60;
        return minutes > 59
                ? (minutes / 60) + " hr " + (minutes % 60) + " min"
                : minutes + " min";
    }
}
