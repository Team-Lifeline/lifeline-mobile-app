package com.example.project_lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Recipient_Dashboard extends AppCompatActivity {

    ImageButton navHome, navRequest, navEmergency, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_dashboard);

        // Initialize Navigation Buttons
        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);

        // Navigation Logic

        // Home is the current page
        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                // Already on Recipient Dashboard
            });
        }

        // Request -> Request_for_bloodActivity
        if (navRequest != null) {
            navRequest.setOnClickListener(v -> {
                Intent intent = new Intent(Recipient_Dashboard.this, Request_for_bloodActivity.class);
                startActivity(intent);
            });
        }

        // Emergency -> Emergency_serviceActivity
        if (navEmergency != null) {
            navEmergency.setOnClickListener(v -> {
                Intent intent = new Intent(Recipient_Dashboard.this, Emergency_serviceActivity.class);
                startActivity(intent);
            });
        }

        // Profile -> UserProfileActivity
        if (navProfile != null) {
            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(Recipient_Dashboard.this, UserProfileActivity.class);
                startActivity(intent);
            });
        }
    }
}
