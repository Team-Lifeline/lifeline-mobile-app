package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Request_for_bloodActivity extends AppCompatActivity {

    EditText inputName, inputQuantity, inputPhone, inputUrgency, inputHospital, inputLocation;
    Button submitRequestBtn, plateletBtn;
    ImageButton navHome, navRequest, navEmergency, navProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_blood);

        // 🔹 Initialize form fields
        inputName = findViewById(R.id.inputName);
        inputQuantity = findViewById(R.id.inputQuantity);
        inputPhone = findViewById(R.id.inputPhone);
        inputUrgency = findViewById(R.id.inputUrgency);
        inputHospital = findViewById(R.id.inputHospital);
        inputLocation = findViewById(R.id.inputLocation);

        submitRequestBtn = findViewById(R.id.submit_Btn);
        plateletBtn = findViewById(R.id.recipient_Btn); // Initialize Platelet button

        // 🔹 Submit button click → for now just show a toast
        submitRequestBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Request submitted!", Toast.LENGTH_SHORT).show();
            // Optional: send data to backend or open Request_detailsActivity
            Intent intent = new Intent(Request_for_bloodActivity.this, Request_detailsActivity.class);
            startActivity(intent);
        });

        // 🔹 Platelet button click → Navigate to Request_for_plateletActivity
        plateletBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Request_for_bloodActivity.this, Request_for_plateletActivity.class);
            startActivity(intent);
        });

        // 🔹 Bottom navigation
        navHome = findViewById(R.id.navHome);
        navRequest = findViewById(R.id.navRequest);
        navEmergency = findViewById(R.id.navEmergency);
        navProfile = findViewById(R.id.navProfile);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(Request_for_bloodActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        navRequest.setOnClickListener(v -> Toast.makeText(this, "Already on Request page", Toast.LENGTH_SHORT).show());

        navEmergency.setOnClickListener(v -> {
            Intent intent = new Intent(Request_for_bloodActivity.this, Emergency_serviceActivity.class);
            startActivity(intent);
        });


        navProfile.setOnClickListener(v -> {
            // Go to Profile page
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
