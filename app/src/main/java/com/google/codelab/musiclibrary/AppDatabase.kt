package com.google.codelab.musiclibrary

import androidx.room.Database
import androidx.room.RoomDatabase
import com.google.codelab.musiclibrary.data.TracksDao
import com.google.codelab.musiclibrary.model.local.TracksEntity

@Database(entities = [TracksEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tracksDao(): TracksDao
}
