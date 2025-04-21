package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chronobooks.R;
import com.example.chronobooks.data.AuthRequest;
import com.example.chronobooks.data.AuthResponse;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    // UI components for registration
    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView linkLogin;

    // AuthService instance to make API requests
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize UI components
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        linkLogin = findViewById(R.id.linkLogin);

        // Create an instance of AuthService using Retrofit
        authService = ApiClient.getRetrofitInstance().create(AuthService.class);

        // Set up the register button click listener
        btnRegister.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Validate input fields
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ensure password and confirm password match
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proceed with the registration process
            register(email, password);
        });

        // Set up the login link click listener to navigate to the login screen
        linkLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    // Method to handle registration API call
    private void register(String email, String password) {
        // Create a request object for registration
        AuthRequest request = new AuthRequest(email, password);

        // Make a network call to register the user
        authService.register(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    // If registration is successful, navigate to the login screen
                    Toast.makeText(RegisterActivity.this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish(); // Close the register activity
                } else {
                    // If registration failed, show an error message
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                // Handle network errors or failure in registration
                Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
