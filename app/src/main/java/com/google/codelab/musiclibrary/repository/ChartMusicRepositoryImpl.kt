package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.businessmodel.ChartBusinessModel
import com.google.codelab.musiclibrary.model.businessmodel.ChartMapper
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ChartMusicRepositoryImpl @Inject constructor(private val remote: RemoteData) :
    ChartMusicRepository {
    override fun fetchChartMusic(startPage: Int): Single<List<ChartBusinessModel>> {
        return remote.fetchChartMusic(startPage)
            .map { ChartMapper.transform(it) }
    }
}
