package com.example.chronobooks.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.chronobooks.R;
import com.example.chronobooks.data.Audiobook;

import java.util.ArrayList;
import java.util.List;

public class AudiobookAdapter extends RecyclerView.Adapter<AudiobookAdapter.ViewHolder> {

    // List to store audiobooks
    private List<Audiobook> audiobookList = new ArrayList<>();

    // Click listener for handling audiobook click events
    private final OnAudiobookClickListener clickListener;

    // Interface for handling audiobook click events
    public interface OnAudiobookClickListener {
        void onAudiobookClick(Audiobook audiobook);
    }

    // Constructor to initialize the click listener
    public AudiobookAdapter(OnAudiobookClickListener listener) {
        this.clickListener = listener;
    }

    // Method to update the audiobook list and refresh the RecyclerView
    public void setAudiobookList(List<Audiobook> list) {
        this.audiobookList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout for each audiobook
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_audiobook, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Audiobook book = audiobookList.get(position);

        // Set the title and author text for each audiobook
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());

        // Load and circle-crop the cover image using Glide
        Glide.with(holder.coverImage.getContext())
                .load(book.getCoverUrl())  // Set the cover URL
                .transform(new CircleCrop()) // Apply circle crop transformation
                .placeholder(R.drawable.ic_book_placeholder) // Placeholder image
                .error(R.drawable.ic_book_placeholder) // Error image if loading fails
                .into(holder.coverImage); // Set the image into the ImageView

        // Set the click listener for the item view
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onAudiobookClick(book); // Trigger the callback
            }
        });
    }

    @Override
    public int getItemCount() {
        return audiobookList.size(); // Return the total number of items in the list
    }

    // ViewHolder class for holding references to views in each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;
        ImageView coverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle); // Title TextView
            author = itemView.findViewById(R.id.textAuthor); // Author TextView
            coverImage = itemView.findViewById(R.id.coverImage); // ImageView for cover image
        }
    }
}
