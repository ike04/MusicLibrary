package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.Failure
import com.google.codelab.musiclibrary.model.FailureType
import com.google.codelab.musiclibrary.model.getMessage
import com.google.codelab.musiclibrary.usecase.ArtistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val usecase: ArtistDetailUseCase) :
    ViewModel() {
    private val _artistSongs: MutableLiveData<ArtistTracks> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var artistTracks: LiveData<ArtistTracks> = _artistSongs
    var errorStream: LiveData<FailureType> = _errorStream

    fun fetchArtistTracks(id: String) {
        usecase.fetchArtistTracks(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _artistSongs.postValue(it)
                },
                onError = {
                    val f = Failure(getMessage(it)) {
                        fetchArtistTracks(id)
                    }
                    _errorStream.postValue(f.message)
                }
            )
    }
}
