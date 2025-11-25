package com.example.project_lifeline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Log_in_Again extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView forgotPassword;
    CheckBox rememberMe;
    
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_again3);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // Initialize Views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.loginButton);
        rememberMe = findViewById(R.id.rememberMe);
        forgotPassword = findViewById(R.id.forgotPassword);

        // Login Button Logic
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> {
                String email = "";
                String password = "";
                
                if (etEmail != null) email = etEmail.getText().toString().trim();
                if (etPassword != null) password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    if (etEmail != null) etEmail.setError("Email is required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    if (etPassword != null) etPassword.setError("Password is required.");
                    return;
                }

                // Authenticate with Firebase
                btnLogin.setEnabled(false); // Prevent double clicks
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                btnLogin.setEnabled(true);
                                if (task.isSuccessful()) {
                                    // Check User Role in Firestore
                                    checkUserRoleAndRedirect(mAuth.getCurrentUser().getUid());
                                } else {
                                    Toast.makeText(Log_in_Again.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            });
        }
    }

    private void checkUserRoleAndRedirect(String userId) {
        fStore.collection("users").document(userId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String userType = document.getString("userType");
                                Intent intent;

                                if ("recipient".equalsIgnoreCase(userType)) {
                                    intent = new Intent(Log_in_Again.this, Recipient_Dashboard.class);
                                } else {
                                    // Default to Donor/Main Dashboard
                                    intent = new Intent(Log_in_Again.this, DashboardActivity.class);
                                }

                                Toast.makeText(Log_in_Again.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                // User authenticated but no data in Firestore (legacy user or error)
                                Toast.makeText(Log_in_Again.this, "User profile not found.", Toast.LENGTH_SHORT).show();
                                // Fallback to main dashboard
                                Intent intent = new Intent(Log_in_Again.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(Log_in_Again.this, "Error fetching profile: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
