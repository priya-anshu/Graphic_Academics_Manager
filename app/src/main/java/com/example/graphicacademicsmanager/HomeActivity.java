package com.example.graphicacademicsmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private TextView studentNameTextView;
    private TextView studentIdTextView;
    private TextView studentEmailTextView;

    private ImageButton ab;
    private ImageButton fb;
    private ImageButton eb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize TextViews
        studentNameTextView = findViewById(R.id.student_name);
        studentIdTextView = findViewById(R.id.student_id);
        studentEmailTextView = findViewById(R.id.gmail);

        // Set default values (You can set values fetched from intent or any other source here)
        setDefaultValues();

        // Initialize Buttons
        ab = findViewById(R.id.ab);
        eb = findViewById(R.id.eb);
        fb = findViewById(R.id.fb1);

        // Set OnClickListener on the Academic button
        ab.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AcademicActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener on the Exam button
        eb.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ExamActivity.class);
            startActivity(intent);
        });

        // Set OnClickListener on the Fees button
        fb.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FeesActivity.class);
            startActivity(intent);
        });
    }

    private void setDefaultValues() {
        studentNameTextView.setText("Priyanshu Dhyani");
        studentIdTextView.setText("23711155");
        studentEmailTextView.setText("priyanshu@gmail.com");
    }
}
