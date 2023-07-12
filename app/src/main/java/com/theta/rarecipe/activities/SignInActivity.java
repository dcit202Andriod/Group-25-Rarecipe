package com.theta.rarecipe.activities;

import static com.theta.rarecipe.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.theta.rarecipe.R;


public class SignInActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(layout.sign_in_activity);

            TextView signUpText = findViewById(id.signUpText);
            signUpText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignInActivity.this, CreateAccount.class);
                    startActivity(intent);
                }
            });
            Button signUpButton = findViewById(R.id.signIn_button);
            signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
