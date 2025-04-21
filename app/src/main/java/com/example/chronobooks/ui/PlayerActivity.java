package com.example.chronobooks.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.chronobooks.R;
import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.data.Chapter;
import com.example.chronobooks.ui.adapters.ChapterAdapter;
import com.example.chronobooks.utils.PlaybackPositionManager;
import com.example.chronobooks.utils.RecentPlaybackManager;

import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    // UI elements for the player screen
    private TextView titleView;
    private ImageButton btnPlayPause, btnPrev, btnNext, btnDownload;
    private ImageView imageCover;
    private RecyclerView recyclerViewChapters;

    // Media player and other utility variables
    private ExoPlayer player;
    private Handler handler = new Handler();
    private Runnable saveProgressRunnable;

    // Variables for audio and playback data
    private String audioUrl;
    private String title;
    private int seekPosition;
    private int selectedIndex = 0;
    private List<Audiobook> audiobookList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Initialize UI references
        titleView = findViewById(R.id.txtTitle);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        btnDownload = findViewById(R.id.btnDownload);
        imageCover = findViewById(R.id.imageCover);
        recyclerViewChapters = findViewById(R.id.recyclerViewChapters);

        // Get the data passed through the Intent
        title = getIntent().getStringExtra("title");
        audioUrl = getIntent().getStringExtra("audioUrl");
        seekPosition = getIntent().getIntExtra("seekPosition", 0);
        selectedIndex = getIntent().getIntExtra("selectedIndex", 0);

        if (getIntent().hasExtra("audiobookList")) {
            audiobookList = (List<Audiobook>) getIntent().getSerializableExtra("audiobookList");
        }

        titleView.setText(title);

        // Load audiobook cover image
        Glide.with(this)
                .load(audiobookList.get(selectedIndex).getCoverUrl())
                .transform(new CircleCrop())  // Apply circle crop to the image
                .placeholder(R.drawable.ic_book_placeholder)
                .into(imageCover);

        // Initialize the player and start playback
        initializePlayer(audioUrl);

        // Save the last played audiobook for resuming playback later
        RecentPlaybackManager.saveLastPlayed(this, title, audioUrl);

        // Set up the play/pause, previous, next, and download buttons
        btnPlayPause.setOnClickListener(v -> togglePlayback());
        btnPrev.setOnClickListener(v -> playPreviousTrack());
        btnNext.setOnClickListener(v -> playNextTrack());
        btnDownload.setOnClickListener(v -> {
            if (audioUrl != null && !audioUrl.isEmpty()) {
                downloadAudioFile(audioUrl, title); // Download audio file if URL is provided
            } else {
                Toast.makeText(this, "No audio URL to download", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up the chapter list in the player UI
        setupChapterList();
        startProgressSaver();
    }

    // Initialize the ExoPlayer and start playback
    private void initializePlayer(String url) {
        player = new ExoPlayer.Builder(this).build();

        // Check if the file is already downloaded, otherwise stream it from URL
        File localFile = new File(getFilesDir(), title + ".mp3");
        if (localFile.exists()) {
            player.setMediaItem(MediaItem.fromUri(Uri.fromFile(localFile)));
        } else {
            player.setMediaItem(MediaItem.fromUri(Uri.parse(url)));
        }

        // Set the starting playback position (either seek position or saved position)
        long lastSaved = PlaybackPositionManager.getPosition(this, url);
        if (seekPosition > 0) {
            player.seekTo(seekPosition);
        } else if (lastSaved > 0) {
            player.seekTo(lastSaved);
        }

        player.prepare();
        player.play();
        btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);  // Change play/pause button to pause icon
    }

    // Toggle between playing and pausing the audio
    private void togglePlayback() {
        if (player.isPlaying()) {
            player.pause();
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play);  // Change to play icon
        } else {
            player.play();
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);  // Change to pause icon
        }
    }

    // Play the previous track in the audiobook list
    private void playPreviousTrack() {
        if (selectedIndex > 0) {
            selectedIndex--;
            switchToTrack(selectedIndex);
        } else {
            Toast.makeText(this, "No previous track", Toast.LENGTH_SHORT).show();
        }
    }

    // Play the next track in the audiobook list
    private void playNextTrack() {
        if (selectedIndex < audiobookList.size() - 1) {
            selectedIndex++;
            switchToTrack(selectedIndex);
        } else {
            Toast.makeText(this, "No next track", Toast.LENGTH_SHORT).show();
        }
    }

    // Switch to a different audiobook track (previous/next)
    private void switchToTrack(int index) {
        Audiobook nextBook = audiobookList.get(index);
        title = nextBook.getTitle();
        audioUrl = nextBook.getAudioUrl();
        titleView.setText(title);

        Glide.with(this)
                .load(nextBook.getCoverUrl())
                .transform(new CircleCrop())  // Apply circle crop to the new cover image
                .placeholder(R.drawable.ic_book_placeholder)
                .into(imageCover);

        // Check if the track is already downloaded, otherwise stream it
        File localFile = new File(getFilesDir(), title + ".mp3");
        if (localFile.exists()) {
            player.setMediaItem(MediaItem.fromUri(Uri.fromFile(localFile)));
        } else {
            player.setMediaItem(MediaItem.fromUri(Uri.parse(audioUrl)));
        }

        player.prepare();
        player.play();
        btnPlayPause.setImageResource(android.R.drawable.ic_media_pause);

        // Save the last played audiobook
        RecentPlaybackManager.saveLastPlayed(this, title, audioUrl);
    }

    // Set up the list of chapters in the audiobook
    private void setupChapterList() {
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(new Chapter("Intro", 0)); // Example chapter data
        chapters.add(new Chapter("Middle", 60));
        chapters.add(new Chapter("End", 120));

        ChapterAdapter adapter = new ChapterAdapter(chapters, millis -> {
            player.seekTo(millis);  // Seek to the selected chapter
            player.play();
        });

        recyclerViewChapters.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChapters.setAdapter(adapter);
    }

    // Periodically save the playback position
    private void startProgressSaver() {
        saveProgressRunnable = () -> {
            if (player != null && audioUrl != null) {
                PlaybackPositionManager.savePosition(this, audioUrl, player.getCurrentPosition());
                handler.postDelayed(saveProgressRunnable, 5000);  // Save position every 5 seconds
            }
        };
        handler.post(saveProgressRunnable);
    }

    // Download the audio file to local storage
    private void downloadAudioFile(String fileUrl, String filenameHint) {
        new Thread(() -> {
            try {
                URL url = new URL(fileUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    runOnUiThread(() -> Toast.makeText(this, "Download failed", Toast.LENGTH_SHORT).show());
                    return;
                }

                File file = new File(getFilesDir(), filenameHint + ".mp3");
                InputStream input = connection.getInputStream();
                FileOutputStream output = new FileOutputStream(file);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }

                output.close();
                input.close();

                runOnUiThread(() -> Toast.makeText(this, "Downloaded: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show());

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Download error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();  // Release the player resources
            handler.removeCallbacks(saveProgressRunnable);  // Stop the progress saving task
        }
    }
}
