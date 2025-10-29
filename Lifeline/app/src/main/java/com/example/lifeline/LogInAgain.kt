package com.example.lifeline

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LogInAgain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in_again)

        // Initializing UI components
        val emailField = findViewById<EditText>(R.id.emailAddress)
        val passwordField = findViewById<EditText>(R.id.password)
        val rememberMeCheckBox = findViewById<CheckBox>(R.id.rememberMe)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val forgotPasswordText = findViewById<TextView>(R.id.forgotPassword)

        // Button click listener
        loginButton.setOnClickListener {
            // Get the entered data
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            // Perform login logic (for now just logging)
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Implement your login logic here
                // Example: use a network call to authenticate
            } else {
                // Handle empty email or password (optional)
            }
        }

        // Forgot Password click listener (add logic here)
        forgotPasswordText.setOnClickListener {
            // Implement logic to reset password
        }
    }
}
