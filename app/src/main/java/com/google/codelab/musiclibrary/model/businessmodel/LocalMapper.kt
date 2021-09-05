package com.google.codelab.musiclibrary.model.businessmodel

import com.google.codelab.musiclibrary.model.local.TracksEntity

class LocalMapper {
    companion object {
        fun transform(entity: List<TracksEntity>): List<Tracks> {
            return entity.map {
                Tracks(
                    title = it.title,
                    subtitle = it.subtitle,
                    images = it.images,
                    url = it.url
                )
            }
        }
    }
}
