package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity {


    // Declaring the Recycle Views
    private RecyclerView trendingRecyclerView;
    private RecyclerView popularCategoryRecyclerView;
    private RecyclerView recentRecipesRecyclerView;

    private RecyclerView popularCreatorsRecyclerView;
    private List<FoodItem> foodItemList;


    // What the Popular Category filter buttons need
    private List<String> filterCategories;
    private TextView activeFilterTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupSearchView();
        setupRecyclerViews();
        loadItemsFromJSON();
        retrieveFilterCategories();
        addFilterTextViews();
    }

    private void setupSearchView() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search recipe");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(HomePageActivity.this, SearchScreen.class);
                intent.putParcelableArrayListExtra("foodItemList", new ArrayList<>(foodItemList));
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void setupRecyclerViews() {
        trendingRecyclerView = findViewById(R.id.trending_section_recycler);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularCategoryRecyclerView = findViewById(R.id.popular_section_recycler);
        popularCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recentRecipesRecyclerView = findViewById(R.id.recent_recipes_recycler);
        recentRecipesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        popularCreatorsRecyclerView = findViewById(R.id.popular_creators_recycler);
        popularCreatorsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void loadItemsFromJSON() {
        String trendingJsonString = loadJSONFromAsset("trending_foods.json");
        String popularJsonString = loadJSONFromAsset("popular_foods.json");
        String recentJsonString = loadJSONFromAsset("recent_foods.json");
        String creatorsJsonString = loadJSONFromAsset("popular_creators");

        if (trendingJsonString != null) {
            List<FoodItem> trendingFoodItemList = parseJSONToFoodItems(trendingJsonString);
            TrendingFoodAdapter trendingFoodAdapter = new TrendingFoodAdapter(trendingFoodItemList);
            trendingRecyclerView.setAdapter(trendingFoodAdapter);
        }

        if (popularJsonString != null) {
            List<FoodItem> popularFoodItemList = parseJSONToFoodItems(popularJsonString);
            PopularFoodAdapter popularFoodAdapter = new PopularFoodAdapter(popularFoodItemList);
            popularCategoryRecyclerView.setAdapter(popularFoodAdapter);
        }

        if (recentJsonString != null) {
            List<FoodItem> recentFoodItemList = parseJSONToFoodItems(recentJsonString);
            RecentFoodAdapter recentFoodAdapter = new RecentFoodAdapter(recentFoodItemList);
            recentRecipesRecyclerView.setAdapter(recentFoodAdapter);
        }

        if (creatorsJsonString != null) {
            List<CreatorItem> creatorItemList = parseJSONToCreatorItems(creatorsJsonString);
            PopularCreatorsAdapter popularCreatorsAdapter = new PopularCreatorsAdapter(creatorItemList);
            popularCreatorsRecyclerView.setAdapter(popularCreatorsAdapter);
        }
    }

    private String loadJSONFromAsset(String filename) {
        String jsonString;
        try {
            InputStream inputStream = getAssets().open(filename);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    private List<FoodItem> parseJSONToFoodItems(String jsonString) {
        List<FoodItem> foodItemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("foodItems");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodObject = jsonArray.getJSONObject(i);
                String name = foodObject.getString("name");
                String imageUrl = foodObject.getString("imageUrl");
                String creatorName = foodObject.getString("creatorName");

                FoodItem foodItem = new FoodItem(name, imageUrl, creatorName);
                foodItemList.add(foodItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return foodItemList;
    }

    private List<CreatorItem> parseJSONToCreatorItems(String jsonString) {
        List<CreatorItem> creatorItemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("creatorItems");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject creatorObject = jsonArray.getJSONObject(i);
                String name = creatorObject.getString("name");
                String imageUrl = creatorObject.getString("imageUrl");

                CreatorItem creatorItem = new CreatorItem(name, imageUrl);
                creatorItemList.add(creatorItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return creatorItemList;
    }

    private void retrieveFilterCategories() {
        filterCategories = new ArrayList<>();
        filterCategories.add("Breakfast");
        filterCategories.add("Animalia");
        filterCategories.add("Salad");
        filterCategories.add("Snacks");
        filterCategories.add("Desserts");
    }

    private void addFilterTextViews() {
        LinearLayout layoutFilterButtons = findViewById(R.id.layout_filter_buttons);

        for (String category : filterCategories) {
            TextView filterTextView = createFilterTextView(category);
            filterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setActiveFilter(filterTextView);
                }
            });

            layoutFilterButtons.addView(filterTextView);
        }
    }

    private TextView createFilterTextView(String category) {
        TextView filterTextView = new TextView(this);
        filterTextView.setText(category);
        filterTextView.setTextColor(Color.parseColor("#772F5E"));
        filterTextView.setBackgroundResource(R.drawable.filter_text_background);
        filterTextView.setPadding(dpToPx(16), dpToPx(8), dpToPx(16), dpToPx(8));
        filterTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMarginEnd(dpToPx(22));
        filterTextView.setLayoutParams(layoutParams);

        return filterTextView;
    }

    private void setActiveFilter(TextView activeTextView) {
        if (activeFilterTextView != null) {
            activeFilterTextView.setBackgroundResource(R.drawable.filter_text_background);
            activeFilterTextView.setTextColor(Color.parseColor("#772F5E"));
        }

        activeTextView.setBackgroundResource(R.drawable.filter_text_background_active);
        activeTextView.setTextColor(Color.WHITE);

        activeFilterTextView = activeTextView;
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
