package com.example.mo.noteappusingroom.POJO;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private INoteDao INoteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteRoomDatabase noteRoomDatabase = NoteRoomDatabase.getInstance(application);
        INoteDao = noteRoomDatabase.noteDao();
        allNotes = noteRoomDatabase.noteDao().loadAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> INoteDao.insertNote(note));
    }

    public void update(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> INoteDao.updateNote(note));
    }

    public void delete(Note note) {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> INoteDao.deleteNote(note));
    }

    public void deleteAllNotes() {
        NoteRoomDatabase.databaseWriteExecutor.execute(() -> INoteDao.deleteAllNotes());
    }

}
