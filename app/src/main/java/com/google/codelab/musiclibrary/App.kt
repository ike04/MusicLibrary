package com.google.codelab.musiclibrary

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.google.codelab.musiclibrary.App.Companion.database
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // AppDatabaseをビルドする
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }
}
