package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.Failure
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChartMusicUseCase {
    fun fetchChartMusic(startPage: Int)

    fun getTracksStream(): Observable<List<Tracks>>

    fun getErrorStream(): Observable<Failure>
}
