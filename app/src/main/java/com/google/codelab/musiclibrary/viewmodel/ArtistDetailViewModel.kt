package com.google.codelab.musiclibrary.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.*
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.usecase.ArtistDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val usecase: ArtistDetailUseCase) :
    ViewModel() {
    private val _artistSongs: MutableLiveData<List<Tracks>> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var artistTracks: LiveData<List<Tracks>> = _artistSongs
    var errorStream: LiveData<FailureType> = _errorStream

    val isLoading = ObservableBoolean(false)

    fun fetchArtistTracks(id: String) {
        usecase.fetchArtistTracks(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.set(true) }
            .subscribeBy(
                onSuccess = {
                    _artistSongs.postValue(it)
                    isLoading.set(false)
                },
                onError = {
                    val f = Failure(getMessage(it)) {
                        fetchArtistTracks(id)
                    }
                    _errorStream.postValue(f.message)
                    isLoading.set(false)
                }
            )
    }
}
