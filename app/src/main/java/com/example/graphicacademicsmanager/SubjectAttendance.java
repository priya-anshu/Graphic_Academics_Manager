package com.example.graphicacademicsmanager;

public class SubjectAttendance {
    public static int attended_lectures;
    public static int total_lectures;

    public SubjectAttendance() {
        // Default constructor required for calls to DataSnapshot.getValue(SubjectAttendance.class)
    }

    public SubjectAttendance(int attended_lectures, int total_lectures) {
        this.attended_lectures = attended_lectures;
        this.total_lectures = total_lectures;
    }
}
