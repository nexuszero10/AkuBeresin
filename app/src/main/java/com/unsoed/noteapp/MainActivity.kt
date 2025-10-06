package com.unsoed.noteapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.unsoed.noteapp.database.NoteDatabase
import com.unsoed.noteapp.repository.NoteRepository
import com.unsoed.noteapp.viewmodel.NoteViewModel
import com.unsoed.noteapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteViewModel: NoteViewModel // agar NoteViewModel dipakai fragment.kt lainnya

    // ketika activicy pertama dibuat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // menampilkan layout activity_main
        setupViewModel()
    }

    private fun setupViewModel() {
        val database = NoteDatabase.invoke(this) // buat instance room db
        val noteRepository = NoteRepository(database) // buat noterepository

        // buat noteviewmodelfactory
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)

        // instance noteviewmodel
        noteViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteViewModel::class.java]
    }
}
