package com.example.mo.noteappusingroom.POJO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "notes_database";
    private static final int NUMBER_OF_THREADS = 5;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile NoteRoomDatabase INSTANCE;

    public static synchronized NoteRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDatabase.class,
                    DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract INoteDao noteDao();
}
