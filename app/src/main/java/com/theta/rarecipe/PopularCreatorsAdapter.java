package com.theta.rarecipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PopularCreatorsAdapter extends RecyclerView.Adapter<PopularCreatorsAdapter.ViewHolder> {

    private final List<CreatorItem> creatorList;

    public PopularCreatorsAdapter(List<CreatorItem> creatorList) {
        this.creatorList = creatorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_creators_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CreatorItem creatorItem = creatorList.get(position);
        holder.creatorNameTextView.setText(creatorItem.getName());
        holder.creatorNameTextView.setText(creatorItem.getName());

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(creatorItem.getImageUrl())
                .into(holder.creatorImageView);
    }

    @Override
    public int getItemCount() {
        return creatorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView creatorImageView;
        public TextView creatorNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            creatorImageView = itemView.findViewById(R.id.creator_image);
            creatorNameTextView = itemView.findViewById(R.id.creator_name);
        }
    }
}
