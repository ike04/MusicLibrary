package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.ChartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class ChartMusicRepositoryImpl: ChartMusicRepository {
    private val remoteData = RemoteData()

    override fun fetchChartMusic(startPage: Int): Single<Response<ChartResponse>> {
        return remoteData.fetchChartMusic(startPage)
    }
}
