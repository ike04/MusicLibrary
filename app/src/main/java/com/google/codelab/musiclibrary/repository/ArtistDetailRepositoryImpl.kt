package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.ArtistTracks
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class ArtistDetailRepositoryImpl: ArtistDetailRepository {
    private val remote = RemoteData()

    override fun fetchArtistTracks(id: String): Single<Response<ArtistTracks>> {
        return remote.fetchArtistTracks(id)
    }
}
