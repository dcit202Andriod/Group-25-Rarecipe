package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView trendingRecyclerView;

    private RecyclerView popularCategoryRecyclerView;

    private List<String> filterCategories;
    private TextView activeFilterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupSearchView();
        setupTrendingSectionRecyclerView();
        setupPopularCategoryRecyclerView();
        loadFoodItemsFromJSON();
        retrieveFilterCategories();
        addFilterTextViews();
    }

    // Creating the hint for the search bar

    private void setupSearchView() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search recipe");
    }

    // Setting Up the recycler views for the layout sections

    private void setupTrendingSectionRecyclerView() {
        trendingRecyclerView = findViewById(R.id.trending_section_recycler);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setupPopularCategoryRecyclerView(){
        popularCategoryRecyclerView = findViewById(R.id.popular_section_recycler);
        popularCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    // Loading the data stored in the json file

    private void loadFoodItemsFromJSON() {
        String jsonString = loadJSONFromAsset();
        if (jsonString != null) {
            List<FoodItem> foodItemList = parseJSONToFoodItems(jsonString);
            FoodAdapter foodAdapter = new FoodAdapter(foodItemList);
            trendingRecyclerView.setAdapter(foodAdapter);
        }
    }

    private String loadJSONFromAsset() {
        String jsonString;
        try {
            InputStream inputStream = getAssets().open("trending_foods.json");
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


    // Generating the popular category buttons

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
            TextView filterTextView = new TextView(this);
            filterTextView.setText(category);
            filterTextView.setTextColor(Color.parseColor("#772F5E"));
            filterTextView.setBackgroundResource(R.drawable.filter_text_background);
            filterTextView.setPadding(16, 8, 16, 8);
            filterTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0, 0, dpToPx(), 0); // Convert dp to pixels
            filterTextView.setLayoutParams(layoutParams);

            filterTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setActiveFilter(filterTextView);
                }
            });

            layoutFilterButtons.addView(filterTextView);
        }
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

    private int dpToPx() {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(20 * density);
    }
}
