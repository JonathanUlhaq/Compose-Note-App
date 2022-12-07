package com.belajar.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.belajar.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Query("SELECT * FROM note_tbl")
    fun showAllNote():Flow<List<Note>>

    @Query("SELECT * FROM note_tbl WHERE id = :id")
    suspend fun showNotebyId(id:String):Note

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun noteUpdate(note:Note)

    @Query("DELETE FROM note_tbl")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note:Note)



}
