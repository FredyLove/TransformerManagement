package com.example.mp3_reader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registration extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirm_password;
    private Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        mAuth = FirebaseAuth.getInstance();
// initialization of variables
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        confirm_password = findViewById(R.id.confirm_password);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String cpassword = confirm_password.getText().toString().trim();

                // Validate the input fields
                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    return;
                }
                if (!password.equals(cpassword)){
                    confirm_password.setError("Password doesn't matches");
                    return;
                }
                // Register the user with Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(registration.this, task -> {
                            if (task.isSuccessful()) {
                                // Registration success, update UI with the signed-in user's information
                                Toast.makeText(registration.this, "Registration successful.",
                                        Toast.LENGTH_SHORT).show();
                                // You can navigate to another activity here if needed
                                Intent Lintent1 = new Intent(registration.this, Login.class);
                                startActivity(Lintent1);
                                finish();
                            } else {
                                // Registration failed, display a message to the user
                                Toast.makeText(registration.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public void Sign_In(View view) {
        Intent Lintent2 = new Intent(registration.this, Login.class);
        startActivity(Lintent2);
    }
}