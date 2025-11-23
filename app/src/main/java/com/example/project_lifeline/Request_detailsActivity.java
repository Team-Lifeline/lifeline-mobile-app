package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Request_detailsActivity extends AppCompatActivity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details); // Matches your XML file

        // Initialize Back Button
        btnBack = findViewById(R.id.btnBack);

        // Back button → DashboardActivity
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(Request_detailsActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Close this activity to avoid multiple stacking / subclass issue
        });
    }
}
