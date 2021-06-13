package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single

interface SearchMusicUseCase {
    fun fetchMusic(keyword: String, offset: Int): Single<SearchResponse>
}
