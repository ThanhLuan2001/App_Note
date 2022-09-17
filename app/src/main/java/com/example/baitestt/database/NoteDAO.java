package com.example.baitestt.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.baitestt.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE date= :date")
    List<Note> check(String date);

    @Query("SELECT * FROM note WHERE date LIKE '%' || :date || '%'")
    List<Note> search(String date );
}
