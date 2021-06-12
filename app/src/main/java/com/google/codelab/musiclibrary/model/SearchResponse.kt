package com.google.codelab.musiclibrary.model

data class SearchResponse(
    val tracks: Tracks,
    val artist: Artists
)

// 曲の情報
data class Tracks(
    val songHits: SongHits
)

data class SongHits(
    val track: List<Track>
)

data class Track(
    val key: String,
    val title: String,
    val subTitle: String,
    val images: Images,
    val url: String,
    val artists: SongArtists
)

data class Images(
    val background: String,
    val coverart: String,
    val coverarthq: String
)

data class SongArtists(
    val id: String,
    val adamid: String
)

// 歌手の情報
data class Artists(
    val hits: ArtistHits
)

data class ArtistHits(
    val artist: List<Artist>
)

data class Artist(
    val avatar: String,
    val id: String,
    val name: String,
    val adamid: String
)
