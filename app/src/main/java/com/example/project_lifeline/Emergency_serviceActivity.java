package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Emergency_serviceActivity extends AppCompatActivity {

    ImageButton navHome, navRequest, navEmergency, navProfile, detailsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_service); 

        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);
        detailsBtn = findViewById(R.id.details);

        // Details button → Hospital Info
        if (detailsBtn != null) {
            detailsBtn.setOnClickListener(v -> {
                Intent intent = new Intent(Emergency_serviceActivity.this, Hospital_infoActivity.class);
                startActivity(intent);
            });
        }

        // Home → Dashboard
        navHome.setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });

        // Donate / Request → Request Page
        navRequest.setOnClickListener(v -> {
            Intent intent = new Intent(this, Request_for_bloodActivity.class);
            startActivity(intent);
            finish();
        });

        // Emergency (current page) → do nothing
        navEmergency.setOnClickListener(v -> {
            // already here
        });

        // Profile → User Profile
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
