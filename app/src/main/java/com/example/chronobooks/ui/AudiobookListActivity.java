package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.chronobooks.R;
import com.example.chronobooks.auth.AuthManager;
import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.ui.adapters.AudiobookAdapter;
import com.example.chronobooks.utils.RecentPlaybackManager;
import com.example.chronobooks.viewmodel.AudiobookViewModel;

import java.util.ArrayList;
import java.util.List;

public class AudiobookListActivity extends AppCompatActivity {

    // UI elements
    private RecyclerView recyclerView;
    private AudiobookAdapter adapter;
    private AudiobookViewModel viewModel;
    private List<Audiobook> allBooks = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout resumeContainer;
    private TextView txtResumeTitle;
    private ImageButton btnResumePlayback, btnPrevious, btnNext;
    private EditText searchInput;

    private int currentIndex = -1;  // 🔁 for prev/next navigation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiobook_list);

        // 🎧 Initialize Resume Section
        resumeContainer = findViewById(R.id.resumeContainer);
        txtResumeTitle = findViewById(R.id.txtResumeTitle);
        btnResumePlayback = findViewById(R.id.btnResumePlayback);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        // 🎯 Pull to Refresh for fetching audiobooks
        swipeRefreshLayout.setOnRefreshListener(() -> fetchData());

        // 🎤 Search Input - live search on text change
        searchInput = findViewById(R.id.searchInput);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                fetchData(); // 🔄 live search
            }
            @Override public void afterTextChanged(Editable s) { }
        });

        // 📚 Initialize RecyclerView and Adapter for Audiobooks
        recyclerView = findViewById(R.id.recyclerViewBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new AudiobookAdapter(audiobook -> {
            // 🎧 Save recent playback and navigate to PlayerActivity
            RecentPlaybackManager.saveLastPlayed(this, audiobook.getTitle(), audiobook.getAudioUrl());
            currentIndex = allBooks.indexOf(audiobook);

            Intent intent = new Intent(this, PlayerActivity.class);
            intent.putExtra("title", audiobook.getTitle());
            intent.putExtra("audioUrl", audiobook.getAudioUrl());
            intent.putExtra("audiobookList", new ArrayList<>(allBooks)); // 🧠 Pass entire audiobook list
            intent.putExtra("selectedIndex", currentIndex); // Track the selected audiobook
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // 📥 ViewModel and data fetching
        viewModel = new ViewModelProvider(this).get(AudiobookViewModel.class);
        observeData();

        // 📤 Fetch data from API
        fetchData();

        // 🎧 Resume buttons setup
        String lastTitle = RecentPlaybackManager.getLastTitle(this);
        String lastUrl = RecentPlaybackManager.getLastAudioUrl(this);
        if (lastTitle != null && lastUrl != null) {
            resumeContainer.setVisibility(View.VISIBLE);
            txtResumeTitle.setText("Resume: " + lastTitle);

            // 🎧 Resume Playback
            btnResumePlayback.setOnClickListener(v -> {
                Intent intent = new Intent(this, PlayerActivity.class);
                intent.putExtra("title", lastTitle);
                intent.putExtra("audioUrl", lastUrl);
                intent.putExtra("audiobookList", new ArrayList<>(allBooks)); // Default pass
                intent.putExtra("selectedIndex", 0); // Resume from the beginning
                startActivity(intent);
            });

            // 🔁 Next/Previous buttons (currently placeholders)
            btnPrevious.setOnClickListener(v -> showToast("Previous track feature in PlayerActivity"));
            btnNext.setOnClickListener(v -> showToast("Next track feature in PlayerActivity"));
        }
    }

    // 🎧 Fetch audiobook data from the backend
    private void fetchData() {
        String token = AuthManager.getToken(this);
        if (token == null || token.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Fetch data based on the token and search query
        viewModel.fetchAudiobooks(token, searchInput.getText().toString());
        swipeRefreshLayout.setRefreshing(true);
    }

    // 🎧 Observe changes in the audiobook data from the ViewModel
    private void observeData() {
        viewModel.getAudiobooks().observe(this, list -> {
            swipeRefreshLayout.setRefreshing(false);
            if (list != null && !list.isEmpty()) {
                allBooks = list;
                adapter.setAudiobookList(list);
            } else {
                Toast.makeText(this, "No audiobooks found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 🔊 Helper function to show a toast message
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
