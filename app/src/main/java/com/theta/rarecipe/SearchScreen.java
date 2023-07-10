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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);



    }
}
