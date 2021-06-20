package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.model.ChartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface ChartMusicRepository {
    fun fetchChartMusic(startPage: Int): Single<Response<ChartResponse>>
}
