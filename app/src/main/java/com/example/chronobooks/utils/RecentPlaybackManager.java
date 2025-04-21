package com.example.chronobooks.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class RecentPlaybackManager {

    // SharedPreferences file name for storing recent playback data
    private static final String PREF_NAME = "RecentPlaybackPrefs";

    // Keys for storing the title and audio URL of the last played audiobook
    private static final String KEY_TITLE = "last_title";
    private static final String KEY_AUDIO_URL = "last_audio_url";

    // Save the last played audiobook's title and audio URL to SharedPreferences
    public static void saveLastPlayed(Context context, String title, String audioUrl) {
        // Get SharedPreferences to store the data
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Save the title and audio URL using the respective keys
        prefs.edit()
                .putString(KEY_TITLE, title)  // Save the title
                .putString(KEY_AUDIO_URL, audioUrl)  // Save the audio URL
                .apply();  // Apply the changes asynchronously
    }

    // Retrieve the title of the last played audiobook
    public static String getLastTitle(Context context) {
        // Get SharedPreferences and retrieve the saved title, or return null if not found
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_TITLE, null);
    }

    // Retrieve the audio URL of the last played audiobook
    public static String getLastAudioUrl(Context context) {
        // Get SharedPreferences and retrieve the saved audio URL, or return null if not found
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_AUDIO_URL, null);
    }
}
