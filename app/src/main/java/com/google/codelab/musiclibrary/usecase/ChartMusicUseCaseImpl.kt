package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.ChartBusinessModel
import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.repository.ChartMusicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ChartMusicUseCaseImpl @Inject constructor(private val repository: ChartMusicRepository) :
    ChartMusicUseCase {
    override fun fetchChartMusic(startPage: Int): Single<List<ChartBusinessModel>> {
        return repository.fetchChartMusic(startPage)
    }
}
