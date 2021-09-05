package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.repository.ChartMusicRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChartMusicUseCaseImpl @Inject constructor(private val repository: ChartMusicRepository) :
    ChartMusicUseCase {
    override fun fetchChartMusic(startPage: Int){
        repository.fetchChartMusic(startPage)
    }

    override fun getTracksStream(): Observable<List<Tracks>> = repository.getTracksStream()
}
