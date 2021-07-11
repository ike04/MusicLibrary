package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class SearchMusicRepositoryImpl @Inject constructor(private val remote: RemoteData) :
    SearchMusicRepository {
    override fun fetchMusic(keyword: String, offset: Int): Single<Response<SearchResponse>> {
        return remote.fetchSearchKeywordInfo(keyword, offset)
    }
}
