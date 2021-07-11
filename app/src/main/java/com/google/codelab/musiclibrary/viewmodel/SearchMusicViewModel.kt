package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.*
import com.google.codelab.musiclibrary.usecase.SearchMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchMusicViewModel @Inject constructor(private val usecase: SearchMusicUseCase) :
    ViewModel() {
    private val _songList: MutableLiveData<Tracks> = MutableLiveData()
    private val _artistList: MutableLiveData<Artists> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var songList: LiveData<Tracks> = _songList
    var artistlist: LiveData<Artists> = _artistList
    var errorStream: LiveData<FailureType> = _errorStream

    fun fetchMusic(keyword: String, offset: Int) {
        usecase.fetchMusic(keyword, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    _songList.postValue(result.tracks)
                    _artistList.postValue(result.artists)
                },
                onError = {
                    val f = Failure(getMessage(it)) {
                        fetchMusic(keyword, offset)
                    }
                    _errorStream.postValue(f.message)
                }
            )
    }
}
