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

    // Default constructor for the fragment
    public AudiobookListFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_audiobook_list, container, false);

        // Initialize RecyclerView to display the audiobook list
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAudiobooks);

        // Set the layout manager for the RecyclerView (vertical list)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the adapter for the RecyclerView to display audiobook cards
        recyclerView.setAdapter(new AudiobookCardView());

        // Return the view for the fragment
        return view;
    }
}
