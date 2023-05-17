package com.example.lifeproplanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

            if (TextUtils.isEmpty(updatedTitle) && TextUtils.isEmpty(updatedContent)) {
                Toast.makeText(EditNoteActivity.this, "Cannot save an empty note", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent();
            intent.putExtra("updatedTitle", updatedTitle);
            intent.putExtra("updatedContent", updatedContent);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
