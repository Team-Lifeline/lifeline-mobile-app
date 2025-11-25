package com.example.project_lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity {

    Button btnCancel, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        // Initialize Buttons
        btnCancel = findViewById(R.id.btnCancel);
        btnLogout = findViewById(R.id.btnLogout);

        // Cancel Button -> Go back to UserProfileActivity
        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> {
                Intent intent = new Intent(LogoutActivity.this, UserProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            });
        }

        // Logout Button -> Go to LoginActivity (Login2)
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear stack
                startActivity(intent);
                finish();
            });
        }
    }
}
