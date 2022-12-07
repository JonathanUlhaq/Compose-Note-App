package com.belajar.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "note_tbl")
data class Note (
                @PrimaryKey
                val id:UUID = UUID.randomUUID(),
                @ColumnInfo(name ="note_title")
                val title:String,
                @ColumnInfo(name ="note_desc")
                val desc:String,
                @ColumnInfo(name ="note_entry_date")
                val entryDate:Date = Date.from(Instant.now())
        )