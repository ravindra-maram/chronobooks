package com.example.chronobooks.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {

    // Constant for the notification channel ID
    public static final String CHANNEL_ID = "playback_channel";

    // Method to create the notification channel for media playback controls
    public static void createPlaybackChannel(Context context) {
        // Check if the device is running Android Oreo (API 26) or later, as notification channels
        // are only supported starting from API 26 (Oreo)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a new NotificationChannel with a unique ID, name, and importance level
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,  // Channel ID used to reference this channel
                    "ChronoBooks Playback",  // Channel name displayed to the user
                    NotificationManager.IMPORTANCE_LOW  // Importance level for the channel
            );

            // Set a description for the channel (visible in system settings)
            channel.setDescription("Media playback controls for ChronoBooks");

            // Get the NotificationManager system service to manage notifications
            NotificationManager manager = context.getSystemService(NotificationManager.class);

            // Create the notification channel if the manager is available
            if (manager != null) {
                manager.createNotificationChannel(channel);  // Create the channel
            }
        }
    }
}
