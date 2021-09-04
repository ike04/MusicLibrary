package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.businessmodel.Artists
import com.google.codelab.musiclibrary.model.businessmodel.ResponseMapper
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ArtistDetailRepositoryImpl @Inject constructor(private val remote: RemoteData) :
    ArtistDetailRepository {
    override fun fetchArtistTracks(id: String): Single<List<Tracks>> {
        return remote.fetchArtistTracks(id)
            .map { ResponseMapper.artistDetailTransform(it) }
    }
}
