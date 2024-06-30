package com.example.graphicacademicsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String DEFAULT_USERNAME = "alpha";
    private static final String DEFAULT_PASSWORD = "Alpha@123";

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
                // Correct username and password
                Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                // Proceed to HomeActivity or perform desired action
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                // Incorrect username or password
                Toast.makeText(MainActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}