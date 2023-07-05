package com.theta.rarecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;

public class WelcomeSectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_section);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search recipe");

    }
}