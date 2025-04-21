package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chronobooks.R;
import com.example.chronobooks.auth.AuthManager;

public class SplashActivity extends AppCompatActivity {

    // Duration for the splash screen in milliseconds (2 seconds)
    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initial animation setup for the logo: scale it down to 0x, 0y, then animate it to full scale
        findViewById(R.id.logo).setScaleX(0f);
        findViewById(R.id.logo).setScaleY(0f);
        findViewById(R.id.logo).animate().scaleX(1f).scaleY(1f).setDuration(800).start();

        // Delay the transition to the next activity (MainActivity or LoginActivity)
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            // Check if the user is logged in
            if (AuthManager.isLoggedIn(SplashActivity.this)) {
                // If logged in, go to MainActivity
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                // If not logged in, go to LoginActivity
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            // Start the selected activity
            startActivity(intent);
            // Close the splash screen activity
            finish();
        }, SPLASH_DURATION);  // Wait for SPLASH_DURATION (2 seconds)
    }
}
