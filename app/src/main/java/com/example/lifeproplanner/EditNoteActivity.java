package com.example.lifeproplanner;

// EditNoteActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSave = findViewById(R.id.buttonSave);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String noteTitle = extras.getString("noteTitle");
            String noteContent = extras.getString("noteContent");

            editTextTitle.setText(noteTitle);
            editTextContent.setText(noteContent);
        }


        buttonSave.setOnClickListener(v -> {

            String updatedTitle = editTextTitle.getText().toString().trim();
            String updatedContent = editTextContent.getText().toString().trim();

            Toast.makeText(EditNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.putExtra("updatedTitle", updatedTitle);
            intent.putExtra("updatedContent", updatedContent);
            setResult(RESULT_OK, intent);

            finish();
        });
    }
}
