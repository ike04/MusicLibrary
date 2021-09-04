package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.SearchResponse
import com.google.codelab.musiclibrary.model.businessmodel.SearchSongBusinessModel
import com.google.codelab.musiclibrary.repository.SearchMusicRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SearchMusicUseCaseImpl @Inject constructor(private val repository: SearchMusicRepository) :
    SearchMusicUseCase {
    override fun fetchMusic(keyword: String, offset: Int): Single<SearchSongBusinessModel> {
        return repository.fetchMusic(keyword, offset)
    }
}
