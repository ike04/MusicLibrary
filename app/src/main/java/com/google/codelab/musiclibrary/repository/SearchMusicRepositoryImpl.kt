package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.businessmodel.ResponseMapper
import com.google.codelab.musiclibrary.model.businessmodel.SearchSongBusinessModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchMusicRepositoryImpl @Inject constructor(private val remote: RemoteData) :
    SearchMusicRepository {
    override fun fetchMusic(keyword: String, offset: Int): Single<SearchSongBusinessModel> {
        return remote.fetchSearchKeywordInfo(keyword, offset)
            .map { ResponseMapper.searchResultTransform(it) }
    }
}
