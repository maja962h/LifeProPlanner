package com.example.lifeproplanner;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPageActivity extends AppCompatActivity {
    private Button getStartedButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        // Initialize views
        getStartedButton = findViewById(R.id.calendar_button);

        // Set click listener for the "Get Started" button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch different activities based on user's choice
                // For example, launch CalendarActivity
                Intent intent = new Intent(LandingPageActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "Notes" button
        Button notesButton = findViewById(R.id.notes_button);
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch NotesActivity
                Intent intent = new Intent(LandingPageActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }
}

