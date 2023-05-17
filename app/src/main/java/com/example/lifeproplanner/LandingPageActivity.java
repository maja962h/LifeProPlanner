package com.example.lifeproplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {
    private Button getStartedButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Set click listener for the "Calendar" button
        Button calendarButton = findViewById(R.id.calendar_button);
        calendarButton.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPageActivity.this, CalendarActivity.class);
            startActivity(intent);
        });

        // Set click listener for the "Notes" button
        Button notesButton = findViewById(R.id.notes_button);
        notesButton.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPageActivity.this, NotesActivity.class);
            startActivity(intent);
        });

        // Set click listener for the "To Do" button
        Button todoButton = findViewById(R.id.todo_button);
        todoButton.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPageActivity.this, TodoActivity.class);
            startActivity(intent);
        });
    }
}

