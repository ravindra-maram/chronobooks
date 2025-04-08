package com.example.chronobooks.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chronobooks.data.Audiobook;
import com.example.chronobooks.repository.AudiobookRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AudiobookViewModel extends ViewModel {

    private final MutableLiveData<List<Audiobook>> audiobookList = new MutableLiveData<>();
    private final AudiobookRepository repository = new AudiobookRepository();

    public LiveData<List<Audiobook>> getAudiobooks() {
        return audiobookList;
    }

    public void fetchAudiobooks(String token) {
        repository.getAllAudiobooks(token).enqueue(new Callback<List<Audiobook>>() {
            @Override
            public void onResponse(Call<List<Audiobook>> call, Response<List<Audiobook>> response) {
                if (response.isSuccessful()) {
                    audiobookList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Audiobook>> call, Throwable t) {
                audiobookList.setValue(null);
            }
        });
    }
}
