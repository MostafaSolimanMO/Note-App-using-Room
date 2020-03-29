package com.example.mo.noteappusingroom.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mo.noteappusingroom.POJO.Note;
import com.example.mo.noteappusingroom.POJO.NoteViewModel;
import com.example.mo.noteappusingroom.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.ItemClickListener {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private FloatingActionButton fab;
    private NoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(this, AddNoteActivity.class)));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);

        viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        viewModel.getAllNotes().observe(this, notes -> noteAdapter.setNotes(notes));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                List<Note> notes = noteAdapter.getNote();
                viewModel.delete(notes.get(position));
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemClickListener(Note note) {
        Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
        int itemId = note.getId();
        String title = note.getTitle();
        String description = note.getDescription();
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_ID, itemId);
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_TITLE, title);
        intent.putExtra(AddNoteActivity.EXTRA_NOTE_DESCRIPTION, description);
        startActivity(intent);
    }
}
