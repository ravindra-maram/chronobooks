package com.example.chronobooks.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chronobooks.R;
import com.example.chronobooks.ui.components.AudiobookCardView;

public class AudiobookListFragment extends Fragment {

    public AudiobookListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_audiobook_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAudiobooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AudiobookCardView());

        return view;
    }
}
