package com.theta.rarecipe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;


public class SearchScreen extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView popularCategoryRecyclerView;
    private RecyclerView recentRecipesRecyclerView;
    private List<FoodItem> foodItemList;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search recipe");

        Intent intent = getIntent();
//        foodItemList = intent.getParcelableArrayListExtra("foodItemList");
        setupRecyclerViews();

    }
    private void setupRecyclerViews() {
        popularCategoryRecyclerView = findViewById(R.id.popular_section_recycler);
        popularCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recentRecipesRecyclerView = findViewById(R.id.recent_recipes_recycler);
        recentRecipesRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        PopularFoodAdapter popularFoodAdapter = new PopularFoodAdapter(foodItemList);
        popularCategoryRecyclerView.setAdapter(popularFoodAdapter);

        RecentFoodAdapter recentFoodAdapter = new RecentFoodAdapter(foodItemList);
        recentRecipesRecyclerView.setAdapter(recentFoodAdapter);
    }

}