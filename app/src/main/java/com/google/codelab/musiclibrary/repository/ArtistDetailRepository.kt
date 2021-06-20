package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.model.ArtistTracks
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ArtistDetailRepository {
    fun fetchArtistTracks(id: String): Single<Response<ArtistTracks>>
}
