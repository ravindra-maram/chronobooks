package com.example.chronobooks.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chronobooks.data.Audiobook;
import com.google.gson.Gson;

public class RecentActivityManager {

    // SharedPreferences file name for storing recent activity data
    private static final String PREF_NAME = "recent_activity";

    // Key for storing the last played audiobook
    private static final String LAST_PLAYED_KEY = "last_played";

    // Save the last played audiobook to SharedPreferences
    public static void saveLastPlayedBook(Context context, Audiobook book) {
        // Get SharedPreferences to store the data
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Convert the Audiobook object to a JSON string and store it in SharedPreferences
        prefs.edit().putString(LAST_PLAYED_KEY, new Gson().toJson(book)).apply();
    }

    // Retrieve the last played audiobook from SharedPreferences
    public static Audiobook getLastPlayedBook(Context context) {
        // Get SharedPreferences to retrieve the stored data
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        // Get the JSON string of the last played audiobook
        String json = prefs.getString(LAST_PLAYED_KEY, null);

        // If a saved audiobook exists, convert the JSON string back to an Audiobook object
        if (json != null) {
            return new Gson().fromJson(json, Audiobook.class);
        }

        // If no last played book is found, return null
        return null;
    }
}
