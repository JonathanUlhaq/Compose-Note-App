package com.belajar.notesapp.util

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampfromDate(date: Date):Long {
        return date.time
    }

    @TypeConverter
    fun dateFromTimeStamp(timeStamp:Long):Date {
        return Date(timeStamp)
    }
}