package com.google.codelab.musiclibrary.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TracksEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,

    val title: String?,
    val subtitle: String?,
    val images: String?,
    val url: String?
)
