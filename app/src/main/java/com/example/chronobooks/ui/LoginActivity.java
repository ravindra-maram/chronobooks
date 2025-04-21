package com.example.chronobooks.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chronobooks.R;
import com.example.chronobooks.auth.AuthManager;
import com.example.chronobooks.data.AuthRequest;
import com.example.chronobooks.data.AuthResponse;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // UI components
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView linkRegister;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        linkRegister = findViewById(R.id.linkRegister);

        // Initialize AuthService to handle login API calls
        authService = ApiClient.getRetrofitInstance().create(AuthService.class);

        // Set up the login button click listener
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validate input fields
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Proceed with login
            login(email, password);
        });

        // Set up the register link click listener to navigate to the registration screen
        linkRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    // Method to handle login with email and password
    private void login(String email, String password) {
        // Create an AuthRequest object with the email and password
        AuthRequest request = new AuthRequest(email, password);

        // Call the login API using Retrofit
        authService.login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                // Handle successful response
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken(); // Retrieve the token from the response

                    // Log the token for debugging
                    Log.d("LOGIN_TOKEN", "Token: " + token);

                    if (token != null && !token.isEmpty()) {
                        // Save the token using AuthManager and navigate to the main activity
                        AuthManager.setToken(LoginActivity.this, token);
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish(); // ✅ Prevent back navigation to the login screen
                    } else {
                        // Handle case where token is missing
                        Toast.makeText(LoginActivity.this, "Login succeeded, but token is missing", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Handle invalid credentials or other errors
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // Handle network error
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
