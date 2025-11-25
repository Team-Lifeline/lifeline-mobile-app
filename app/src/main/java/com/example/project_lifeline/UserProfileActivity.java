package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    ImageButton navHome, navRequest, navEmergency, navProfile;
    ImageView backButton, profileIcon;
    LinearLayout btnLogout;
    TextView btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize Views
        backButton = findViewById(R.id.backButton);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditProfile = findViewById(R.id.btnEditProfile);

        // Bottom Navigation
        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);

        // Back Button
        backButton.setOnClickListener(v -> finish());

        // Edit Profile
        btnEditProfile.setOnClickListener(v -> 
            Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        );

        // Logout Logic: Go to LogoutActivity (activity_logout.xml)
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, LogoutActivity.class);
            startActivity(intent);
        });

        // Navigation Logic
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfileActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        navRequest.setOnClickListener(v -> {
             Intent intent = new Intent(UserProfileActivity.this, Request_for_bloodActivity.class);
             startActivity(intent);
        });

        navEmergency.setOnClickListener(v -> {
             Intent intent = new Intent(UserProfileActivity.this, Emergency_serviceActivity.class);
             startActivity(intent);
        });
        
        navProfile.setOnClickListener(v -> 
             Toast.makeText(this, "Already on Profile", Toast.LENGTH_SHORT).show()
        );
    }
}
