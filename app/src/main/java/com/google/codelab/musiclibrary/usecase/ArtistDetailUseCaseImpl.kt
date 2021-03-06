package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.repository.ArtistDetailRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ArtistDetailUseCaseImpl @Inject constructor(private val repository: ArtistDetailRepository) :
    ArtistDetailUseCase {
    override fun fetchArtistTracks(id: String): Single<List<Tracks>> {
        return repository.fetchArtistTracks(id)
    }
}
