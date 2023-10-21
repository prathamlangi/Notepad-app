package com.example.noteapp.fragment

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.model.Notes
import com.example.noteapp.R
import com.example.noteapp.viewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentEditNoteBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.Exception
import java.util.Date


@Suppress("DEPRECATION")
class EditNoteFragment : Fragment() {

    private val oldNotes by navArgs<EditNoteFragmentArgs>()
    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!


    private val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        // Set the title in the action bar
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = "Edit Notes"

        binding.edtTitle.setText(oldNotes.data.title)
        binding.edtNotes.setText(oldNotes.data.notes)
        binding.btnSaveNote.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        val notesData =
            Notes(oldNotes.data.id, title = title, notes = notes, date = notesDate.toString())
        viewModel.updateNotes(notesData)
        Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)

    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialoge_delete)
            bottomSheet.show()

            val txtYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val txtNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)
            txtYes?.setOnClickListener {
                try{
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
//                Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)
                    val action = EditNoteFragmentDirections.actionEditNoteFragmentToHomeFragment()
                    findNavController().navigate(action)
            }catch (e:Exception){
                Toast.makeText(requireContext(),"Some Error",Toast.LENGTH_SHORT).show()
            }
            }
            txtNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}