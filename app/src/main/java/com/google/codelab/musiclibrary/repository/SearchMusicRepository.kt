package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface SearchMusicRepository {
    fun fetchMusic(keyword: String, offset: Int): Single<Response<SearchResponse>>
}
