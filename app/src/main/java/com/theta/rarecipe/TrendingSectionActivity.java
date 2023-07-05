package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TrendingSectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trending_section);

        // Read the JSON file and parse its contents into a JSONArray
        String jsonString = loadJSONFromAsset();

        try {
            // Parse the JSON string into a JSONArray
            assert jsonString != null;
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("foodItems");

            // Iterate through the JSON array and extract the relevant data for each FoodItem object
            List<FoodItem> foodItemList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject foodObject = jsonArray.getJSONObject(i);
                String name = foodObject.getString("name");
                String imageUrl = foodObject.getString("imageUrl");
                String creatorName = foodObject.getString("creatorName");

                // Create a new FoodItem object and add it to the list
                FoodItem foodItem = new FoodItem(name, imageUrl, creatorName);
                foodItemList.add(foodItem);
            }

            // Create an instance of the adapter, set it as the adapter for the RecyclerView,
            // and provide the data to populate the list
            FoodAdapter foodAdapter = new FoodAdapter(foodItemList);
            RecyclerView recyclerView = findViewById(R.id.food_recycler_view);
            recyclerView.setAdapter(foodAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Helper method to load JSON data from a file in the assets folder
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
}