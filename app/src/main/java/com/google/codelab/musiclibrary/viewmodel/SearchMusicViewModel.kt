package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.Artists
import com.google.codelab.musiclibrary.model.Tracks
import com.google.codelab.musiclibrary.usecase.SearchMusicUseCaseImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchMusicViewModel : ViewModel() {
    private val usecase = SearchMusicUseCaseImpl()
    private val _songList: MutableLiveData<Tracks> = MutableLiveData()
    private val _artistList: MutableLiveData<Artists> = MutableLiveData()
    var songList: LiveData<Tracks> = _songList
    var artistlist: LiveData<Artists> = _artistList

    fun fetchMusic(keyword: String, offset: Int) {
        usecase.fetchMusic(keyword, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                        _songList.postValue(result.tracks)
                        _artistList.postValue(result.artists)
                },
                onError = {}
            )
    }
}
