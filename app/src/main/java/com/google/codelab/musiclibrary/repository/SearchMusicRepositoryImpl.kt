package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class SearchMusicRepositoryImpl: SearchMusicRepository {
    private val remoteData = RemoteData()

    override fun fetchMusic(keyword: String, offset: Int): Single<Response<SearchResponse>> {
        return remoteData.fetchSearchKeywordInfo(keyword, offset)
    }
}
