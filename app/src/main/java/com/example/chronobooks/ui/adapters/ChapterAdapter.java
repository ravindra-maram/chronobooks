package com.example.chronobooks.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chronobooks.R;
import com.example.chronobooks.data.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    // List to store the chapters
    private final List<Chapter> chapterList;

    // Listener for handling chapter item click events
    private final OnChapterClickListener listener;

    // Interface for handling chapter click events
    public interface OnChapterClickListener {
        // Method to handle the click event for a chapter, passing the start time in milliseconds
        void onChapterClick(long timeMillis); // Just pass start time
    }

    // Constructor to initialize the chapter list and listener
    public ChapterAdapter(List<Chapter> chapters, OnChapterClickListener listener) {
        this.chapterList = chapters;
        this.listener = listener;
    }

    // ViewHolder class to hold the references to the chapter item views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the title TextView for each chapter item
            title = itemView.findViewById(R.id.chapterTitle);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each chapter
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the chapter at the current position
        Chapter chapter = chapterList.get(position);

        // Set the title of the chapter in the TextView
        holder.title.setText(chapter.getTitle());

        // Set a click listener on the item view
        holder.itemView.setOnClickListener(v -> {
            // Call the listener's method and pass the start time of the chapter
            listener.onChapterClick(chapter.getStartTimeMillis());
        });
    }

    @Override
    public int getItemCount() {
        // Return the number of chapters in the list
        return chapterList.size();
    }
}
