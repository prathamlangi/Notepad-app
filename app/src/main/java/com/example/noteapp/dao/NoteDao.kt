package com.example.noteapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.model.Notes

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getAllNotes(): LiveData<List<Notes>>
//    @Query("SELECT * FROM Note WHERE id = :noteId")
//    fun getNoteById(noteId: Long): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Update
    fun updateNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    fun deleteNotes(id:Int)
}