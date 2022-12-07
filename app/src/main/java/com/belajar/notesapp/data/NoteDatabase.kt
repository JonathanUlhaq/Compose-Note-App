package com.belajar.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.belajar.notesapp.model.Note
import com.belajar.notesapp.util.DateConverter
import com.belajar.notesapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract  class NoteDatabase:RoomDatabase() {
    abstract fun databaseDAO():DatabaseDAO
}