package com.google.codelab.musiclibrary.model

import retrofit2.Response

class ChartMapper {
    companion object {
        fun transform(response: Response<ChartResponse>): List<ChartBusinessModel> {
            return response.body()?.tracks?.let {
                it.map { song ->
                    ChartBusinessModel(
                        title = song.title,
                        subtitle = song.subtitle,
                        images = song.images?.coverart,
                        url = song.url
                    )
                }
            } ?: emptyList()
        }
    }
}
