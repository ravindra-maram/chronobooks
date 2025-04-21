package com.example.chronobooks.playback;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.chronobooks.R;
import com.example.chronobooks.ui.PlayerActivity;

public class PlaybackService extends Service {

    // Constant for the notification ID to manage the notification
    public static final int NOTIF_ID = 101;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Extract the title of the track from the intent (if provided)
        String title = intent.getStringExtra("title");

        // Create an Intent to open the PlayerActivity when the notification is tapped
        Intent notifIntent = new Intent(this, PlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notifIntent, PendingIntent.FLAG_IMMUTABLE);

        // Build the notification using NotificationCompat.Builder
        Notification notification = new NotificationCompat.Builder(this, "playback_channel")
                .setContentTitle("Now Playing") // Title of the notification
                .setContentText(title != null ? title : "ChronoBooks") // If title is null, display "ChronoBooks"
                .setSmallIcon(R.drawable.ic_play) // Small icon displayed in the notification
                .setContentIntent(pendingIntent) // Set the pending intent to open PlayerActivity when tapped
                .setOngoing(true) // Make the notification ongoing (it won't be dismissed by swipe)
                .build();

        // Start the service as a foreground service with the notification
        startForeground(NOTIF_ID, notification);

        return START_STICKY; // The service will restart if it is killed
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null; // This service does not support binding, so return null
    }
}
