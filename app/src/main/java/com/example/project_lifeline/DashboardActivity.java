package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    LinearLayout navHome, navRequest, navEmergency , navProfile;
    Button btnViewRequest;
    ImageView profileIcon;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard); // make sure this matches your XML file name


        // 🔹 Bottom navigation
        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);


        // Initialize other elements
        btnViewRequest = findViewById(R.id.btnViewRequest);
        profileIcon = findViewById(R.id.profileIcon);

        // Top profile icon click
        profileIcon.setOnClickListener(v ->
                Toast.makeText(this, "Profile icon clicked", Toast.LENGTH_SHORT).show()
        );

        // View Request button click
        btnViewRequest.setOnClickListener(v ->
                Toast.makeText(this, "View Request button clicked", Toast.LENGTH_SHORT).show()
        );

        // Bottom navigation clicks
        navHome.setOnClickListener(v ->
                Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        );

        navRequest.setOnClickListener(v ->
                Toast.makeText(this, "Request clicked", Toast.LENGTH_SHORT).show()
        );

        navEmergency.setOnClickListener(v ->
                Toast.makeText(this, "Emergency clicked", Toast.LENGTH_SHORT).show()
        );

        navProfile.setOnClickListener(v ->
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
        );
        btnViewRequest.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Request_detailsActivity.class);
            startActivity(intent);
        });
        navRequest.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Request_for_bloodActivity.class);
            startActivity(intent);
        });

        navEmergency.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, Emergency_serviceActivity.class);
            startActivity(intent);
        });


    }
}
