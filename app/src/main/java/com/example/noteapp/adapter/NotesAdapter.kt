package com.example.noteapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.fragment.HomeFragmentDirections
import com.example.noteapp.model.Notes
import com.example.noteapp.databinding.ItemNotesBinding

class NotesAdapter(private var notesList: List<Notes>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun filtering(newFilterList: ArrayList<Notes>){
        notesList=newFilterList
        notifyDataSetChanged()
    }

    class NotesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=notesList.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data=notesList[position]
        holder.binding.notesTitle.text=data.title
        holder.binding.notesDesc.text=data.notes
        holder.binding.notesDate.text=data.date

        holder.binding.root.setOnClickListener {
            val action=HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}