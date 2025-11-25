package com.example.project_lifeline;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Donor_sign_up extends AppCompatActivity {

    Button btnCreateAccount, btnRecipient;
    TextView tvLogin;
    EditText inputFullName, inputEmail, inputAge, inputGender, inputLocation, inputPassword;
    Spinner spinnerBlood;
    
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_sign_up);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Initialize Views
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        tvLogin = findViewById(R.id.tvLogin);
        btnRecipient = findViewById(R.id.recipient_Btn);
        
        inputFullName = findViewById(R.id.inputFullName);
        inputEmail = findViewById(R.id.inputEmail);
        spinnerBlood = findViewById(R.id.spinnerBlood);
        inputAge = findViewById(R.id.donor_age);
        inputGender = findViewById(R.id.donor_gender);
        inputLocation = findViewById(R.id.inputLocation);
        inputPassword = findViewById(R.id.inputPassword);

        // Setup Blood Group Spinner
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bloodGroups);
        spinnerBlood.setAdapter(adapter);

        // Login Link
        if (tvLogin != null) {
            tvLogin.setOnClickListener(v -> {
                Intent intent = new Intent(Donor_sign_up.this, Log_in_Again.class);
                startActivity(intent);
                finish();
            });
        }

        // Recipient Button
        if (btnRecipient != null) {
            btnRecipient.setOnClickListener(v -> {
                try {
                    Intent intent = new Intent(Donor_sign_up.this, Recipient_SignUp.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "Recipient page error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Create Account Button
        if (btnCreateAccount != null) {
            btnCreateAccount.setOnClickListener(v -> {
                String fullName = inputFullName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String age = inputAge.getText().toString().trim();
                String gender = inputGender.getText().toString().trim();
                String location = inputLocation.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String bloodGroup = spinnerBlood.getSelectedItem().toString();

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
                                    // 2. Save User Details to Firestore
                                    String userID = mAuth.getCurrentUser().getUid();
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("fullName", fullName);
                                    user.put("email", email);
                                    user.put("bloodGroup", bloodGroup);
                                    user.put("age", age);
                                    user.put("gender", gender);
                                    user.put("location", location);
                                    user.put("userType", "donor"); // Tag user as donor

                                    fStore.collection("users").document(userID).set(user)
                                            .addOnSuccessListener(aVoid -> {
                                                Toast.makeText(Donor_sign_up.this, "Account Created & Data Saved.", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Donor_sign_up.this, DashboardActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                                finish();
                                            })
                                            .addOnFailureListener(e -> {
                                                Toast.makeText(Donor_sign_up.this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });

                                } else {
                                    Toast.makeText(Donor_sign_up.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            });
        }
    }
}
