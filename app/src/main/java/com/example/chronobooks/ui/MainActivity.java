package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chronobooks.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the Start button
        Button btnStart = findViewById(R.id.btnStart);

        // Set the click listener for the Start button
        btnStart.setOnClickListener(v -> {
            // Show a toast message to indicate that the Audiobook List is being opened
            Toast.makeText(this, "Opening Audiobook List...", Toast.LENGTH_SHORT).show();

            // Start the AudiobookListActivity to navigate to the audiobook list
            startActivity(new Intent(this, AudiobookListActivity.class));
        });
    }
}
