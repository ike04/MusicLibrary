package com.google.codelab.musiclibrary.data

import com.google.codelab.musiclibrary.data.ShazamClient.retrofit
import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RemoteData {
    fun fetchSearchKeywordInfo(keyword: String, offset: Int): Single<Response<SearchResponse>> {
        return retrofit.create(ApiRequest::class.java).fetchSearchResult(keyword, offset, LIMIT)
    }

    companion object {
        private const val LIMIT = 5
    }
}
