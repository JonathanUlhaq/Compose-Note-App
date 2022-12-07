package com.belajar.notesapp.di

import android.content.Context
import androidx.room.Room
import com.belajar.notesapp.data.DatabaseDAO
import com.belajar.notesapp.data.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NoteModule {

    @Singleton
    @Provides
    fun provideDatabaseDAO(database:NoteDatabase):DatabaseDAO = database.databaseDAO()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context:Context):NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "db_note"
    )
        .fallbackToDestructiveMigration()
        .build()

}