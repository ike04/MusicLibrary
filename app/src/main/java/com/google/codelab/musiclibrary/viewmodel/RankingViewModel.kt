package com.google.codelab.musiclibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.model.ChartTracks
import com.google.codelab.musiclibrary.usecase.ChartMusicUseCaseImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class RankingViewModel : ViewModel() {
    private val usecase = ChartMusicUseCaseImpl()
    private val _songList: MutableLiveData<List<ChartTracks>> = MutableLiveData()
    var songList: LiveData<List<ChartTracks>> = _songList

    fun fetchRankingMusic(startPage: Int) {
        usecase.fetchChartMusic(startPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    _songList.postValue(result.tracks)
                },
                onError = {}
            )
    }
}
