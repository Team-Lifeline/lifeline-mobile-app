package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2); // make sure this file exists in res/layout

        // 🔹 Initialize buttons
        Button loginButton = findViewById(R.id.loginButton);
        Button signupButton = findViewById(R.id.signupButton);
        TextView goToSignup = findViewById(R.id.goToSignup);

        // 🔹 When "Login" is clicked → go to Log_in_AgainActivity
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Log_in_Again.class);
            startActivity(intent);
        });

        // 🔹 When "Signup" is clicked → go to Donor_sign_up
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Donor_sign_up.class);
            startActivity(intent);
        });

        // 🔹 Also make "Don't have an account?" text go to Donor_sign_up
        goToSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, Donor_sign_up.class);
            startActivity(intent);
        });
    }
}
