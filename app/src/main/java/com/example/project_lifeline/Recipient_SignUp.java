package com.example.project_lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Recipient_SignUp extends AppCompatActivity {

    Button btnDonor, btnCreateAccount;
    TextView tvLogin;
    EditText inputFullName, inputEmail, inputLocation, inputPassword;
    Spinner spinnerBlood;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_sign_up);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Initialize Views
        btnDonor = findViewById(R.id.Donor_Btn);
        tvLogin = findViewById(R.id.tvLogin);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        
        inputFullName = findViewById(R.id.inputFullName);
        inputEmail = findViewById(R.id.inputEmail);
        inputLocation = findViewById(R.id.inputLocation);
        inputPassword = findViewById(R.id.inputPassword);
        spinnerBlood = findViewById(R.id.spinnerBlood);

        // Setup Blood Group Spinner
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups);
        spinnerBlood.setAdapter(adapter);

        // "I am a donor" Button -> Navigate to Donor_sign_up
        if (btnDonor != null) {
            btnDonor.setOnClickListener(v -> {
                Intent intent = new Intent(Recipient_SignUp.this, Donor_sign_up.class);
                startActivity(intent);
            });
        }

        // Login Link -> Go to Log_in_Again activity
        if (tvLogin != null) {
            tvLogin.setOnClickListener(v -> {
                Intent intent = new Intent(Recipient_SignUp.this, Log_in_Again.class);
                startActivity(intent);
                finish();
            });
        }

        // Create Account Button -> Register Recipient
        if (btnCreateAccount != null) {
            btnCreateAccount.setOnClickListener(v -> {
                String fullName = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String location = inputLocation.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String bloodGroup = spinnerBlood.getSelectedItem() != null ? spinnerBlood.getSelectedItem().toString() : "";

                if (TextUtils.isEmpty(email)) { inputEmail.setError("Email is required."); return; }
                if (TextUtils.isEmpty(password)) { inputPassword.setError("Password is required."); return; }
                if (password.length() < 6) { inputPassword.setError("Password must be >= 6 characters."); return; }
                if (TextUtils.isEmpty(fullName)) { inputFullName.setError("Name is required."); return; }

                // 1. Create user in Firebase Auth
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // 2. Save User Details to Firestore as 'recipient'
                                    String userID = mAuth.getCurrentUser().getUid();
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("fullName", fullName);
                                    user.put("email", email);
                                    user.put("bloodGroup", bloodGroup);
                                    user.put("location", location);
                                    user.put("userType", "recipient"); // Tag user as recipient

                                    fStore.collection("users").document(userID).set(user)
                                            .addOnSuccessListener(aVoid -> {
                                                Toast.makeText(Recipient_SignUp.this, "Recipient Account Created.", Toast.LENGTH_SHORT).show();
                                                try {
                                                    Intent intent = new Intent(Recipient_SignUp.this, Recipient_Dashboard.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                    startActivity(intent);
                                                    finish();
                                                } catch (Exception e) {
                                                    // Show a descriptive error if navigation fails
                                                    Toast.makeText(Recipient_SignUp.this, "Error navigating to dashboard: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            })
                                            .addOnFailureListener(e -> {
                                                Toast.makeText(Recipient_SignUp.this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });

                                } else {
                                    Toast.makeText(Recipient_SignUp.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            });
        }
    }
}
