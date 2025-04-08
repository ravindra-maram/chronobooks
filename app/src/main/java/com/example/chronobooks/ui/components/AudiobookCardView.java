package com.example.chronobooks.ui.components;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.chronobooks.R;

public class AudiobookCardView extends RecyclerView.Adapter<AudiobookCardView.ViewHolder> {

    private final String[] mockData = {"Book One", "Book Two", "Book Three"};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_audiobook_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mockData[position]);
    }

    @Override
    public int getItemCount() {
        return mockData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tvAudiobookTitle);
        }
    }
}
