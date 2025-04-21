package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chronobooks.R;
import com.example.chronobooks.data.Chapter;
import com.example.chronobooks.ui.adapters.ChapterAdapter;

import java.util.List;

public class AudiobookDetailActivity extends AppCompatActivity {

    // Views for displaying audiobook title and chapters
    private TextView titleTextView;
    private RecyclerView chapterRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiobook_detail);

        // Initialize the views
        titleTextView = findViewById(R.id.textAudiobookTitle);
        chapterRecyclerView = findViewById(R.id.recyclerChapters);

        // Retrieve data from the Intent
        String title = getIntent().getStringExtra("title");
        String audioUrl = getIntent().getStringExtra("audioUrl");
        List<Chapter> chapters = (List<Chapter>) getIntent().getSerializableExtra("chapters");

        // Set the audiobook title if it exists
        if (title != null) {
            titleTextView.setText(title);
        }

        // Create the ChapterAdapter and pass the chapter data and click listener
        ChapterAdapter adapter = new ChapterAdapter(chapters, timeMillis -> {
            // Create an Intent to open the PlayerActivity and pass necessary data
            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra("audioUrl", audioUrl); // Pass the audio URL
            intent.putExtra("title", title); // Pass the title
            intent.putExtra("seekPosition", (int) timeMillis); // Pass the chapter start time in milliseconds
            startActivity(intent); // Start the PlayerActivity
        });

        // Set up the RecyclerView with a LinearLayoutManager and the adapter
        chapterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chapterRecyclerView.setAdapter(adapter);
    }
}
