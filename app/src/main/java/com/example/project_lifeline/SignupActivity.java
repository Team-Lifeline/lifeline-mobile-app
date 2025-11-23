package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnSignup;
    ImageView backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup); // make sure this matches your signup XML

        // Initialize views
        etName = findViewById(R.id.etFullName);       // Name input
        etEmail = findViewById(R.id.etEmail);     // Email input
        etPassword = findViewById(R.id.etPassword); // Password input
        btnSignup = findViewById(R.id.btnSignIn); // Signup button
        backButton = findViewById(R.id.backButton); // Back arrow

        // 🔹 Back button → go back to LoginActivity
        backButton.setOnClickListener(v -> finish());

        // 🔹 Signup button → validate and go to Dashboard
        btnSignup.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // ✅ Here you can add your database/Firebase registration logic later

                // Navigate to Dashboard
                Intent intent = new Intent(SignupActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish(); // close SignupActivity so user can't go back
            }
        });
    }
}
