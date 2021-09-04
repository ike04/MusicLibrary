package com.google.codelab.musiclibrary.model.businessmodel

import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.model.SearchResponse
import retrofit2.Response

class ChartMapper {
    companion object {
        fun transform(response: Response<ChartResponse>): List<Tracks> {
            return response.body()?.tracks?.let {
                it.map { song ->
                    Tracks(
                        title = song.title,
                        subtitle = song.subtitle,
                        images = song.images?.coverart,
                        url = song.url
                    )
                }
            } ?: emptyList()
        }

        fun searchResultTransform(response: Response<SearchResponse>): SearchSongBusinessModel? {
            return response.body()?.let {
                SearchSongBusinessModel(
                    tracks = trackTransform(it.tracks),
                    artists = artistTransform(it.artists)
                )
            }
        }

        private fun trackTransform(response: com.google.codelab.musiclibrary.model.Tracks): List<Tracks> {
            return response.hits.map {
                Tracks(
                    title = it.track.title,
                    subtitle = it.track.subtitle,
                    images = it.track.images?.coverart,
                    url = it.track.url
                )
            }
        }

        private fun artistTransform(response: com.google.codelab.musiclibrary.model.Artists): List<Artists> {
            return response.hits.map {
                Artists(
                    avatar = it.artist.avatar,
                    id = it.artist.id,
                    name = it.artist.name
                )
            }
        }
    }
}
