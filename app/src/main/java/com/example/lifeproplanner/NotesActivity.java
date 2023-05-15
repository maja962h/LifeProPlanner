package com.example.lifeproplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        noteList = new ArrayList<>();
        // Add some sample notes
        noteList.add(new Note("Note 1", "Content 1"));
        noteList.add(new Note("Note 2", "Content 2"));
        noteList.add(new Note("Note 3", "Content 3"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);

        FloatingActionButton fabCreateNote = findViewById(R.id.fabCreateNote);
        fabCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNote();
            }
        });
    }

    @Override
    public void onNoteClick(int position) {
        Note note = noteList.get(position);
        Toast.makeText(this, "Clicked on note: " + note.getTitle(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("noteTitle", note.getTitle());
        intent.putExtra("noteContent", note.getContent());
        startActivity(intent);
    }

    public void createNewNote() {
        // Open the EditNoteActivity to create a new note
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
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
}