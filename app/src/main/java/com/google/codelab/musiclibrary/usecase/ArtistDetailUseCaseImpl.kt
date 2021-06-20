package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.repository.ArtistDetailRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ArtistDetailUseCaseImpl : ArtistDetailUseCase {
    private val repository = ArtistDetailRepositoryImpl()

    override fun fetchArtistTracks(id: String): Single<ArtistTracks> {
        return repository.fetchArtistTracks(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.body()
            }
    }
}
