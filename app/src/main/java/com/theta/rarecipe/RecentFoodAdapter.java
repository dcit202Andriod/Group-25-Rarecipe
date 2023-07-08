package com.theta.rarecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecentFoodAdapter extends RecyclerView.Adapter<RecentFoodAdapter.ViewHolder> {

    private final List<FoodItem> foodList;
    public Context context;

    public RecentFoodAdapter(List<FoodItem> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recent_food_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodList.get(position);
        holder.foodNameTextView.setText(foodItem.getName());
        holder.creatorNameTextView.setText(foodItem.getCreatorName());

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(foodItem.getImageUrl())
                .into(holder.foodImageView);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView foodImageView;
        public TextView foodNameTextView;
        public TextView creatorNameTextView;
        private CardView recentCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.food_image);
            foodNameTextView = itemView.findViewById(R.id.food_name);
            creatorNameTextView = itemView.findViewById(R.id.creator_name);
            recentCardView = itemView.findViewById(R.id.popular_cardView);
        }
    }
}
