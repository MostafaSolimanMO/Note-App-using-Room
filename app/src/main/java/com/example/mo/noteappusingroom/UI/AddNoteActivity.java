package com.example.mo.noteappusingroom.UI;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.mo.noteappusingroom.POJO.Note;
import com.example.mo.noteappusingroom.POJO.NoteViewModel;
import com.example.mo.noteappusingroom.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "NoteId";
    public static final String EXTRA_NOTE_TITLE = "NoteTitle";
    public static final String EXTRA_NOTE_DESCRIPTION = "NoteDescription";

    private EditText titleEdit, noteEdit;
    private NoteViewModel noteViewModel;
    private Bundle extras = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        titleEdit = findViewById(R.id.add_note_title);
        noteEdit = findViewById(R.id.add_note);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        setTitle("Add Note");
        extras = getIntent().getExtras();
        if (extras != null) {
            setTitle("Edit Note");
            String title = extras.getString(EXTRA_NOTE_TITLE);
            String description = extras.getString(EXTRA_NOTE_DESCRIPTION);
            titleEdit.setText(title);
            noteEdit.setText(description);
        }

    }

    @Override
    public void onBackPressed() {
        String title = String.valueOf(titleEdit.getText());
        String description = String.valueOf(noteEdit.getText());
        if (extras != null) {
            int id = extras.getInt(EXTRA_NOTE_ID);
            Note note = new Note(title, description);
            note.setId(id);
            noteViewModel.update(note);
            finish();
        } else {
            if (!title.isEmpty() || !description.isEmpty()) noteViewModel.insert(new Note(title, description));
            finish();
        }
    }
}
