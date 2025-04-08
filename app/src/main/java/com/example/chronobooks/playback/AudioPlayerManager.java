package com.example.chronobooks.playback;

import android.content.Context;
import android.net.Uri;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

public class AudioPlayerManager {
    private static ExoPlayer player;

    public static void initializePlayer(Context context) {
        if (player == null) {
            player = new ExoPlayer.Builder(context).build();
        }
    }

    public static void playAudio(Context context, String audioUrl) {
        initializePlayer(context);
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(audioUrl));
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }

    public static void stopPlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    public static boolean isPlaying() {
        return player != null && player.isPlaying();
    }
}
