package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.ChartResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class ChartMusicRepositoryImpl @Inject constructor(private val remote: RemoteData) :
    ChartMusicRepository {
    override fun fetchChartMusic(startPage: Int): Single<Response<ChartResponse>> {
        return remote.fetchChartMusic(startPage)
    }
}
