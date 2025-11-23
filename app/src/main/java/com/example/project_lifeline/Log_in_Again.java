package com.example.project_lifeline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Log_in_Again extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_again3); // make sure this XML exists

        etEmail = findViewById(R.id.etEmail);          // match XML
        etPassword = findViewById(R.id.etPassword);    // match XML
        btnLogin = findViewById(R.id.loginButton);     // match XML
        backButton = findViewById(R.id.backButton);    // match XML

        // Back button → finish
        backButton.setOnClickListener(v -> finish());

        // Login button → Dashboard
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, DashboardActivity.class));
                finish();
            }
        });
    }
}
