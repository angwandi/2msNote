package com.example.demad.a2msnote.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY priority")
    List<NoteEntry> loadAllNotes();

    @Insert
    void insertNote(NoteEntry noteEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(NoteEntry noteEntry);

    @Delete
    void deleteNote(NoteEntry noteEntry);

    //used for note update
    //Create a Query method named loadNoteById that receives
    // an int id and returns a NoteEntry Object
    // The query for this method should get all
    // the data for that id in the notes table
    @Query("SELECT * FROM notes WHERE id =:id")
    NoteEntry loadNoteById(int id);
}
