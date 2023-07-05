package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
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

    private RecyclerView recyclerView;
    private List<String> filterCategories;
    private Button activeFilterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setupSearchView();
        setupRecyclerView();
        loadFoodItemsFromJSON();
        retrieveFilterCategories();
        addFilterButtons();
    }

    private void setupSearchView() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search recipe");
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.food_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void loadFoodItemsFromJSON() {
        String jsonString = loadJSONFromAsset();
        if (jsonString != null) {
            List<FoodItem> foodItemList = parseJSONToFoodItems(jsonString);
            FoodAdapter foodAdapter = new FoodAdapter(foodItemList);
            recyclerView.setAdapter(foodAdapter);
        }
    }

    private String loadJSONFromAsset() {
        String jsonString;
        try {
            InputStream inputStream = getAssets().open("data.json");
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

    private void retrieveFilterCategories() {
        filterCategories = new ArrayList<>();
        filterCategories.add("Breakfast");
        filterCategories.add("Animalia");
        filterCategories.add("Salad");
        filterCategories.add("Snacks");
        filterCategories.add("Desserts");
    }

    private void addFilterButtons() {
        LinearLayout layoutFilterButtons = findViewById(R.id.layout_filter_buttons);

        for (String category : filterCategories) {
            Button filterButton = new Button(this);
            filterButton.setText(category);
            filterButton.setTextColor(Color.parseColor("#772F5E"));
            filterButton.setBackgroundResource(R.drawable.filter_button_background);
            filterButton.setPadding(16, 8, 16, 8);
            filterButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setActiveFilter(filterButton);
                    setActiveFilter(filterButton);
                }
            });

            layoutFilterButtons.addView(filterButton);
        }
    }

    private void setActiveFilter(Button activeButton) {
        if (activeFilterButton != null) {
            activeFilterButton.setBackgroundResource(R.drawable.filter_button_background);
            activeFilterButton.setTextColor(Color.parseColor("#772F5E"));
        }

        activeButton.setBackgroundResource(R.drawable.filter_button_background_active);
        activeButton.setTextColor(Color.WHITE);

        activeFilterButton = activeButton;
    }
}
