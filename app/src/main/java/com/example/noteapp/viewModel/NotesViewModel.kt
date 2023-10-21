package com.example.noteapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.noteapp.database.NotesDatabase
import com.example.noteapp.model.Notes
import com.example.noteapp.repository.NotesRepo

class NotesViewModel(application: Application):AndroidViewModel(application) {

    private val repository:NotesRepo
    init {
        val dao=NotesDatabase.getDatabaseInstance(application).noteDao()
        repository= NotesRepo(dao)
    }
    fun addNotes(notes: Notes){
        repository.insertNotes(notes)
    }
    fun getNotes():LiveData<List<Notes>> =repository.getAllNotes()

    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}