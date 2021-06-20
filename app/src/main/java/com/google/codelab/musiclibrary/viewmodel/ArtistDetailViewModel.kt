package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.FailureType
import com.google.codelab.musiclibrary.model.Track
import com.google.codelab.musiclibrary.usecase.ArtistDetailUseCaseImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class ArtistDetailViewModel : ViewModel() {
    private val usecase = ArtistDetailUseCaseImpl()
    private val _artistSongs: MutableLiveData<ArtistTracks> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var artistTracks: LiveData<ArtistTracks> = _artistSongs
    var errorStream: LiveData<FailureType> = _errorStream

    fun fetchArtistTracks(id: String) {
        usecase.fetchArtistTracks(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onSuccess = {
                    _artistSongs.postValue(it)
                },
                onError = {}
            )
    }
}
