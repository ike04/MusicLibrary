package com.google.codelab.musiclibrary.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.codelab.musiclibrary.model.local.TracksEntity

@Dao
interface TracksDao {
    // ローカルからデータを取得
    @Query("SELECT * FROM tracks")
    fun loadTracks(): List<TracksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTracks(tracksEntity: TracksEntity)
}
