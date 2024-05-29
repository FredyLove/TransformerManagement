package com.example.mp3_reader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button login_button;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    return;
                }

                // Sign in the user with Firebase
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, task -> {
                            if (task.isSuccessful()) {
                                // Login success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                String userEmail = user.getEmail();
                                emailEditText.setText("Logged in as: " + userEmail);
                                Toast.makeText(Login.this, "Login successful.",
                                        Toast.LENGTH_SHORT).show();
                                // You can navigate to another activity here if needed
                                Intent intent = new Intent(Login.this, Drawer.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // Login failed, display a message to the user
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void Sign_Up(View view) {
        Intent intent = new Intent(Login.this, registration.class);
        startActivity(intent);
    }
}