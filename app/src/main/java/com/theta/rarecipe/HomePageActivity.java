package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);

        // Set the query hint text programmatically
        searchView.setQueryHint("Search recipe");
    }
}