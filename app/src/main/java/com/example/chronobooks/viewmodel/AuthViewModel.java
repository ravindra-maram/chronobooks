package com.example.chronobooks.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chronobooks.auth.AuthManager;
import com.example.chronobooks.data.AuthRequest;
import com.example.chronobooks.data.AuthResponse;
import com.example.chronobooks.network.ApiClient;
import com.example.chronobooks.network.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    // MutableLiveData to store the result of login success or failure
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    // Getter method for LiveData to observe login success/failure in the UI
    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    // 🔐 Method to handle login logic and save the token upon successful login
    public void login(Context context, String email, String password) {
        // Create a request object with email and password
        AuthRequest request = new AuthRequest(email, password);

        // Create an instance of AuthService to make the login API call
        AuthService service = ApiClient.getRetrofitInstance().create(AuthService.class);

        // Make an asynchronous login request
        service.login(request).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                // If the response is successful and contains a valid token
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    // If the token is not empty, save it using AuthManager
                    if (token != null && !token.isEmpty()) {
                        AuthManager.setToken(context, token);
                        loginSuccess.setValue(true);  // Set login success to true
                    } else {
                        loginSuccess.setValue(false);  // Token is missing, login failed
                    }
                } else {
                    loginSuccess.setValue(false);  // Response was not successful, login failed
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                // In case of network error or failure, set login success to false
                loginSuccess.setValue(false);
            }
        });
    }
}
