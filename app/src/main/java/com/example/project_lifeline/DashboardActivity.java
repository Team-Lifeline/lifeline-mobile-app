package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    ImageButton navHome, navRequest, navEmergency , navProfile;
    Button btnViewRequest;
    ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_dashboard); 

            // 🔹 Bottom navigation
            navHome = findViewById(R.id.navHome);
            navRequest = findViewById(R.id.navRequest);
            navEmergency = findViewById(R.id.navEmergency);
            navProfile = findViewById(R.id.navProfile);

            // Initialize other elements
            btnViewRequest = findViewById(R.id.btnViewRequest);
            profileIcon = findViewById(R.id.profileIcon);

            // Safety check for null views (in case XML is mismatching)
            if (navHome == null || navRequest == null || navEmergency == null || navProfile == null) {
                Toast.makeText(this, "Error: Navigation buttons not found!", Toast.LENGTH_LONG).show();
                return;
            }

            // Top profile icon click
            if (profileIcon != null) {
                profileIcon.setOnClickListener(v -> {
                    Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
                    startActivity(intent);
                });
            }

            // View Request button click
            if (btnViewRequest != null) {
                btnViewRequest.setOnClickListener(v ->
                        Toast.makeText(this, "View Request button clicked", Toast.LENGTH_SHORT).show()
                );
                btnViewRequest.setOnClickListener(v -> {
                    Intent intent = new Intent(DashboardActivity.this, Request_detailsActivity.class);
                    startActivity(intent);
                });
            }

            // Bottom navigation clicks
            navHome.setOnClickListener(v ->
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
            );

            navRequest.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, Request_for_bloodActivity.class);
                startActivity(intent);
            });


            navEmergency.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, Emergency_serviceActivity.class);
                startActivity(intent);
            });

            navProfile.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
                startActivity(intent);
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading Dashboard: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
