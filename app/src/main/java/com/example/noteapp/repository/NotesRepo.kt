package com.example.noteapp.repository

import androidx.lifecycle.LiveData
import com.example.noteapp.dao.NoteDao
import com.example.noteapp.model.Notes

class NotesRepo(private val dao: NoteDao) {
    fun getAllNotes(): LiveData<List<Notes>> {
        return dao.getAllNotes()
    }

//    override suspend fun getNoteById(noteId: Long): Note? = noteDao.getNoteById(noteId)

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun updateNotes(note: Notes) {
        dao.updateNotes(note)
    }

    fun deleteNotes(id:Int) {
        dao.deleteNotes(id)
    }
}