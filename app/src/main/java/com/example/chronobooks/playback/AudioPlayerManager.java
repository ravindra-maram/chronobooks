package com.example.chronobooks.playback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

public class AudioPlayerManager {
    // ExoPlayer instance for managing audio playback
    private static ExoPlayer player;

    // Context for interacting with the application environment
    private static Context context;

    // Initialize the AudioPlayerManager with the application context
    public static void init(Context ctx) {
        context = ctx.getApplicationContext();

        // Initialize the ExoPlayer only once
        if (player == null) {
            player = new ExoPlayer.Builder(context).build();
        }
    }

    // Play an audio stream from the provided URL
    public static void playStream(String url) {
        if (player == null) return;

        // Create a MediaItem from the URL
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));

        // Set the media item to the player, prepare, and start playback
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();

        // Start the playback service to manage playback in the background
        startService(url);
    }

    // Pause the audio playback
    public static void pause() {
        if (player != null) player.pause();
    }

    // Release the ExoPlayer resources and stop the playback service
    public static void release() {
        if (player != null) {
            player.release();
            player = null;
        }
        stopService();
    }

    // Start the playback service for background management
    private static void startService(String url) {
        // Create an Intent to start the PlaybackService with the audio URL
        Intent serviceIntent = new Intent(context, PlaybackService.class);
        serviceIntent.setAction("ACTION_START");
        serviceIntent.putExtra("AUDIO_URL", url);

        // Start the service in the foreground
        context.startForegroundService(serviceIntent);
    }

    // Stop the playback service
    private static void stopService() {
        // Create an Intent to stop the PlaybackService
        Intent stopIntent = new Intent(context, PlaybackService.class);
        stopIntent.setAction("ACTION_STOP");

        // Start the service to stop playback
        context.startService(stopIntent);
    }
}
