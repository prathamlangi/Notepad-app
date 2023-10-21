package com.example.noteapp.fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.noteapp.model.Notes
import com.example.noteapp.R
import com.example.noteapp.viewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentCreateNoteBinding
import java.util.Date

class CreateNoteFragment : Fragment() {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    private val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNoteBinding.inflate(inflater,container,false)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Add Notes"

        binding.btnSaveNote.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }
    private fun createNotes(it:View?) {
        val title=binding.edtTitle.text.toString()
        val notes=binding.edtNotes.text.toString()
        val d= Date()
        val notesDate:CharSequence= DateFormat.format("MMMM d, yyyy",d.time)
        val notesData=Notes(null, title = title, notes = notes, date = notesDate.toString())
        viewModel.addNotes(notesData)
        Toast.makeText(requireContext(),"Note Added Successfully",Toast.LENGTH_SHORT).show()
       Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }
}