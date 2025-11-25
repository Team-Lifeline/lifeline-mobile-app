package com.example.project_lifeline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Request_for_plateletActivity extends AppCompatActivity {

    Button bloodBtn;
    ImageButton navHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_platelet);

        bloodBtn = findViewById(R.id.Donor_Btn);
        navHome = findViewById(R.id.navHome);

        // Navigate to Request_for_bloodActivity when Blood button is clicked
        bloodBtn.setOnClickListener(v -> {
             Intent intent = new Intent(Request_for_plateletActivity.this, Request_for_bloodActivity.class);
             startActivity(intent);
        });

        // Navigate to DashboardActivity when Home button is clicked
        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(Request_for_plateletActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        Toast.makeText(this, "Platelet Request", Toast.LENGTH_SHORT).show();
    }
}
