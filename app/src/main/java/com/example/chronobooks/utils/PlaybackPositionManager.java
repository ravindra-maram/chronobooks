package com.example.chronobooks.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PlaybackPositionManager {

    // SharedPreferences file name for storing playback data
    private static final String PREF_NAME = "ChronoBooksPlaybackPrefs";

    // Key prefix to identify position keys for each audio URL
    private static final String KEY_PREFIX = "position_";

    // Save the playback position for a given audio URL
    public static void savePosition(Context context, String audioUrl, long position) {
        // Get SharedPreferences to store the position data
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Store the position using the audio URL as part of the key
        prefs.edit().putLong(KEY_PREFIX + audioUrl, position).apply();
    }

    // Get the saved playback position for a given audio URL
    public static long getPosition(Context context, String audioUrl) {
        // Get SharedPreferences to retrieve the position data
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Return the saved position or 0 if no position is found for the audio URL
        return prefs.getLong(KEY_PREFIX + audioUrl, 0);
    }
}
