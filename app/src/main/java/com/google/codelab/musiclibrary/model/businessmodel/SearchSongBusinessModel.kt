package com.google.codelab.musiclibrary.model.businessmodel

data class SearchSongBusinessModel(
    val tracks: List<Tracks>,
    val artists: List<Artists>
)

data class Tracks(
    val title: String?,
    val subtitle: String?,
    val images: String?,
    val url: String?
)

data class Artists(
    val avatar: String?,
    val id: String,
    val name: String?
)
