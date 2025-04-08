package com.example.chronobooks.playback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class PlaybackService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String audioUrl = intent.getStringExtra("audio_url");
        AudioPlayerManager.playAudio(this, audioUrl);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AudioPlayerManager.stopPlayer();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
