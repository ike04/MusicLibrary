package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.businessmodel.ChartBusinessModel
import com.google.codelab.musiclibrary.repository.ChartMusicRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChartMusicUseCaseImpl @Inject constructor(private val repository: ChartMusicRepository) :
    ChartMusicUseCase {
    override fun fetchChartMusic(startPage: Int): Single<List<ChartBusinessModel>> {
        return repository.fetchChartMusic(startPage)
    }
}
