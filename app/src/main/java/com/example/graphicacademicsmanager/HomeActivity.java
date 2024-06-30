package com.example.graphicacademicsmanager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    private TextView studentNameTextView;
    private TextView studentIdTextView;
    private TextView studentCourseTextView;
    private TextView studentMobileTextView;
    private TextView studentAddressTextView;
    private TextView studentEmailTextView;

    private ProgressBar pBar;
    private TextView progressText;

    private DatabaseReference studentRef; // Reference to Firebase student details

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pBar = findViewById(R.id.progress_Bar);
        progressText = findViewById(R.id.text_percent);


        // Initialize TextViews
        studentNameTextView = findViewById(R.id.student_name);
        studentIdTextView = findViewById(R.id.student_id);
        //studentCourseTextView = findViewById(R.id.text_student_course);
       // studentMobileTextView = findViewById(R.id.text_student_mobile);
       // studentAddressTextView = findViewById(R.id.text_student_address);
        studentEmailTextView = findViewById(R.id.gmail);

        // Initialize Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        studentRef = database.getReference("student_details").child("student1"); // Adjust path as per your structure

        // Fetch student details from Firebase
        fetchStudentDetails();
    }

    private void fetchStudentDetails() {
        studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String studentName = dataSnapshot.child("studentname").getValue(String.class);
                    String studentId = dataSnapshot.child("studentid").getValue(String.class);
                    String studentCourse = dataSnapshot.child("studentcourse").getValue(String.class);
                    String studentMobile = dataSnapshot.child("mobilenumber").getValue(String.class);
                    String studentAddress = dataSnapshot.child("address").getValue(String.class);
                    String studentEmail = dataSnapshot.child("gmail").getValue(String.class);

                    // Update UI with fetched data
                    updateUI(studentName, studentId, studentCourse, studentMobile, studentAddress, studentEmail);
                } else {
                    Toast.makeText(HomeActivity.this, "Student details not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(HomeActivity.this, "Failed to fetch student details: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(String name, String id, String course, String mobile, String address, String email) {
        studentNameTextView.setText(name);
        studentIdTextView.setText(id);
        studentCourseTextView.setText(course);
        studentMobileTextView.setText(mobile);
        studentAddressTextView.setText(address);
        studentEmailTextView.setText(email);
    }

    private void updateProgressBarColor(int progress) {
        if (progress <= 50) {
            pBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (progress <= 75) {
            pBar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else {
            pBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }
    }

}

