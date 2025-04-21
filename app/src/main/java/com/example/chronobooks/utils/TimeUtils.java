package com.example.chronobooks.utils;

public class TimeUtils {

    // Method to format the duration in seconds into a "mm:ss" format
    public static String formatDuration(int seconds) {
        // Calculate the minutes and remaining seconds
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        // Return the formatted string in "mm:ss" format
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    // Method to format the time in a more verbose format (e.g., "1 hr 30 min" or "45 min")
    public static String formatTimeVerbose(int seconds) {
        // Calculate the total minutes
        int minutes = seconds / 60;

        // If the total minutes are greater than 59, convert to hours and minutes
        return minutes > 59
                ? (minutes / 60) + " hr " + (minutes % 60) + " min"  // Format as "X hr Y min"
                : minutes + " min";  // If less than 60 minutes, format as "X min"
    }
}
