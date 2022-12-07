package com.belajar.notesapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belajar.notesapp.model.Note
import com.belajar.notesapp.repositories.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(val repository: NoteRepository) :ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.showAllNote().distinctUntilChanged().collect{
                if (it.isNullOrEmpty()) {
                    Log.d("PESAN: ", "LIST NULL")
                } else {
                    _noteList.value = it
                }
            }
        }
    }



     fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

     fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

     fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

     fun deleteAllNote() = viewModelScope.launch {
        repository.deleteAll()
    }

}