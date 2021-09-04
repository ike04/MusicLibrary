package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.Failure
import com.google.codelab.musiclibrary.model.FailureType
import com.google.codelab.musiclibrary.model.businessmodel.Artists
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.model.getMessage
import com.google.codelab.musiclibrary.usecase.SearchMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchMusicViewModel @Inject constructor(private val usecase: SearchMusicUseCase) :
    ViewModel() {
    private val _songList: MutableLiveData<List<Tracks>> = MutableLiveData()
    private val _artistList: MutableLiveData<List<Artists>> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var songList: LiveData<List<Tracks>> = _songList
    var artistlist: LiveData<List<Artists>> = _artistList
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
