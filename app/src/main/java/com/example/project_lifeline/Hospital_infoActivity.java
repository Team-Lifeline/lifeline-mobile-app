package com.example.project_lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Hospital_infoActivity extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info);

        // Removed EdgeToEdge/ViewCompat code to avoid crash on missing 'main' ID
        
        backButton = findViewById(R.id.backButton);

        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                Intent intent = new Intent(Hospital_infoActivity.this, Emergency_serviceActivity.class);
                // Optional: clear top if you want fresh start of Emergency activity, or just finish()
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }
    }
}
