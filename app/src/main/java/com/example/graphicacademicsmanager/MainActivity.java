package com.example.graphicacademicsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("student_login");

        loginButton.setOnClickListener(v -> {
            String enteredUsername = usernameEditText.getText().toString().trim();
            String enteredPassword = passwordEditText.getText().toString().trim();

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the entered username exists in the database
            userRef.orderByChild("studentid").equalTo(enteredUsername).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            String correctPassword = userSnapshot.child("password").getValue(String.class);
                            Log.d("MainActivity", "Correct Password from DB: " + correctPassword);
                            if (correctPassword != null && correctPassword.equals(enteredPassword)) {
                                // Correct username and password
                                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                String studentId = userSnapshot.child("studentid").getValue(String.class);
                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("STUDENT_ID", studentId);
                                startActivity(intent);
                                finish(); // Optional: Close the login activity
                            } else {
                                // Incorrect password
                                Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        // Username not found
                        Toast.makeText(MainActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("MainActivity", "Database error: " + databaseError.getMessage());
                    Toast.makeText(MainActivity.this, "Failed to login: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
