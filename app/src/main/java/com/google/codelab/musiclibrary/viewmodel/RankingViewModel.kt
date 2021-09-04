package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.Failure
import com.google.codelab.musiclibrary.model.FailureType
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.model.getMessage
import com.google.codelab.musiclibrary.usecase.ChartMusicUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(private val usecase: ChartMusicUseCase) : ViewModel() {
    private val _songList: MutableLiveData<List<Tracks>> = MutableLiveData()
    private val _errorStream: MutableLiveData<FailureType> = MutableLiveData()
    var songList: LiveData<List<Tracks>> = _songList
    var errorStream: LiveData<FailureType> = _errorStream

    fun fetchRankingMusic(startPage: Int) {
        usecase.fetchChartMusic(startPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { song ->
                    _songList.postValue(song)
                },
                onError = {
                    val f = Failure(getMessage(it)) {
                        fetchRankingMusic(startPage)
                    }
                    _errorStream.postValue(f.message)
                }
            )
    }
}
