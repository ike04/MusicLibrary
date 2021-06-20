package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.ArtistTracks
import io.reactivex.rxjava3.core.Single

interface ArtistDetailUseCase {
    fun fetchArtistTracks(id: String):Single<ArtistTracks>
}
