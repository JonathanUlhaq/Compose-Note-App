package com.belajar.notesapp.repositories

import com.belajar.notesapp.data.DatabaseDAO
import com.belajar.notesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val databaseDAO: DatabaseDAO) {
    suspend fun insertNote(note:Note) = databaseDAO.insertNote(note)
    suspend fun updateNote(note: Note) = databaseDAO.noteUpdate(note)
    suspend fun delete(note: Note) = databaseDAO.deleteNote(note)
    suspend fun deleteAll() = databaseDAO.deleteAllNotes()
    suspend fun showAllNote():Flow<List<Note>> = databaseDAO.showAllNote().flowOn(Dispatchers.IO).conflate()


}