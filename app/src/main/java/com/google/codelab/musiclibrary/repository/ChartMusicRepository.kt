package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChartMusicRepository {
    fun fetchChartMusic(startPage: Int)

    fun getTracksStream(): Observable<List<Tracks>>
}
