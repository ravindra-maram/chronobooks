package com.example.chronobooks.ui.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chronobooks.R;

public class AudiobookCardView extends RecyclerView.Adapter<AudiobookCardView.ViewHolder> {

    // Array to hold some mock audiobook data (replace with real data in production)
    private final String[] mockData = {"Book One", "Book Two", "Book Three"};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each audiobook card item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_audiobook_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Set the title of the audiobook from the mock data
        holder.title.setText(mockData[position]);
    }

    @Override
    public int getItemCount() {
        // Return the number of items (mock data length in this case)
        return mockData.length;
    }

    // ViewHolder class to hold the view elements for each audiobook card item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        // Constructor to initialize the view elements
        public ViewHolder(View view) {
            super(view);
            // Find the TextView by its ID and assign it to the title variable
            title = view.findViewById(R.id.tvAudiobookTitle);
        }
    }
}
