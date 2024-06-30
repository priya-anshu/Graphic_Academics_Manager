package com.example.graphicacademicsmanager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private ProgressBar pBar;
    private TextView progressText;
    private int totalLectures = 40; // Change this to the total number of lectures
    private int attendedLectures = 25; // Change this to the number of lectures attended
    private int attendancePercentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pBar = findViewById(R.id.progress_Bar);
        progressText = findViewById(R.id.text_percent);

        updateAttendancePercentage();
    }

    private void updateAttendancePercentage() {
        attendancePercentage = (int)(((float)attendedLectures / (float)totalLectures) * 100);
        progressText.setText(attendancePercentage + "%");
        pBar.setProgress(attendancePercentage);
        updateProgressBarColor(attendancePercentage);
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
