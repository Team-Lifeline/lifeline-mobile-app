package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class Emergency_serviceActivity extends AppCompatActivity {

    ImageButton navHome, navRequest, navEmergency, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_service); // use your emergency XML name

        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);

        // Home → Dashboard
        navHome.setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });

        // Donate / Request → (replace TargetActivity with your real activity)
        navRequest.setOnClickListener(v -> {
            Intent intent = new Intent(this, Request_for_bloodActivity.class);
            startActivity(intent);
            finish();
        });

        // Emergency (current page) → do nothing or refresh
        navEmergency.setOnClickListener(v -> {
            // already here, so no navigation
        });

        // Profile → (replace ProfileActivity with your real activity)
        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, User_profileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
