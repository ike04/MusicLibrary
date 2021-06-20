package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.repository.ChartMusicRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ChartMusicUseCaseImpl: ChartMusicUseCase {
    private val repository = ChartMusicRepositoryImpl()

    override fun fetchChartMusic(startPage: Int): Single<ChartResponse> {
        return repository.fetchChartMusic(startPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.body()
            }
    }
}
