package com.example.noteapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.adapter.NotesAdapter
import com.example.noteapp.model.Notes
import com.example.noteapp.R
import com.example.noteapp.viewModel.NotesViewModel
import com.example.noteapp.databinding.FragmentHomeBinding

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by viewModels()
//    private var oldMyNotes = arrayListOf<Notes>()
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        viewModel.getNotes().observe(
            viewLifecycleOwner
        ) { notesList ->
//            oldMyNotes = notesList as ArrayList<Notes>
            binding.RvNotes.layoutManager = LinearLayoutManager(requireContext())
            adapter = NotesAdapter(notesList)
            binding.RvNotes.adapter = adapter
        }

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//        val item = menu.findItem(R.id.app_bar_search)
//        val searchView = item.actionView as SearchView
//        searchView.queryHint = "Enter Notes Here"
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//               return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                notesFiltering(newText)
//                return true
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }

//    private fun notesFiltering(newText: String?) {
//        val newFilterList = arrayListOf<Notes>()
//        for (i in oldMyNotes) {
//            if (i.title?.contains(newText!!) == true) {
//                newFilterList.add(i)
//            }
//        }
//        adapter.filtering(newFilterList)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

