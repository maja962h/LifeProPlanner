package com.example.lifeproplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private List<Note> noteList;
    private int editingNotePosition;
    private static final int REQUEST_CODE_EDIT_NOTE = 1;
    private static final int REQUEST_CODE_CREATE_NOTE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        noteList = new ArrayList<>();
        // Add some sample notes
        noteList.add(new Note("Note 1", "Content 1"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton fabCreateNote = findViewById(R.id.fabCreateNote);
        fabCreateNote.setOnClickListener(v -> createNewNote());
    }

    @Override
    public void onNoteClick(int position) {
        Note note = noteList.get(position);
        Toast.makeText(this, "Clicked on note: " + note.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("noteTitle", note.getTitle());
        intent.putExtra("noteContent", note.getContent());
        editingNotePosition = position; // Set the editing note position
        startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE);
    }

    public void createNewNote() {
        // Open the EditNoteActivity to create a new note
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CREATE_NOTE);
    }

    public void deleteNote(int position) {
        // Remove the note from the list
        noteList.remove(position);
        // Notify the adapter about the item removal
        noteAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onDeleteClick(int position) {
        deleteNote(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_EDIT_NOTE) {
                String updatedTitle = data.getStringExtra("updatedTitle");
                String updatedContent = data.getStringExtra("updatedContent");

                // Update the note in the list
                Note updatedNote = noteList.get(editingNotePosition);
                updatedNote.setTitle(updatedTitle);
                updatedNote.setContent(updatedContent);
                // Notify the adapter about the data change
                noteAdapter.notifyItemChanged(editingNotePosition);
            } else if (requestCode == REQUEST_CODE_CREATE_NOTE) {
                String newTitle = data.getStringExtra("updatedTitle");
                String newContent = data.getStringExtra("updatedContent");

                // Create a new note
                Note newNote = new Note(newTitle, newContent);
                noteList.add(newNote);
                // Notify the adapter about the new item
                noteAdapter.notifyItemInserted(noteList.size() - 1);
                // Scroll to the newly added note
                recyclerView.scrollToPosition(noteList.size() - 1);
            }
        }
    }
}