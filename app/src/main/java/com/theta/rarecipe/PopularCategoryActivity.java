package com.theta.rarecipe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PopularCategoryActivity extends AppCompatActivity {
    private List<String> filterCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_category);

        // Call method to retrieve filter categories and populate the list
        retrieveFilterCategories();

        // Proceed with Step 3: Add Filter Buttons Dynamically
        addFilterButtons();
    }

    private void retrieveFilterCategories() {
        filterCategories = new ArrayList<>();
        filterCategories.add("BreakFasty");
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
            // Set appropriate styling and properties for the filter button
            // Set click listener or callback for handling user interactions
            layoutFilterButtons.addView(filterButton);
        }
    }
}
