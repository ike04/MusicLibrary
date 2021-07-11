package com.google.codelab.musiclibrary.data

import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class RemoteData @Inject constructor(private val api: ApiRequest) {
    fun fetchSearchKeywordInfo(keyword: String, offset: Int): Single<Response<SearchResponse>> {
        return api.fetchSearchResult(keyword, offset, LIMIT)
    }

    fun fetchChartMusic(startPage: Int): Single<Response<ChartResponse>> {
        return api.fetchCharts(COUNTRY_KEY, 20, startPage)
    }

    fun fetchArtistTracks(id: String): Single<Response<ArtistTracks>> {
        return api.fetchArtistTracks((id))
    }

    companion object {
        private const val LIMIT = 5
        private const val COUNTRY_KEY = "ip-city-chart-1850147"
    }
}
