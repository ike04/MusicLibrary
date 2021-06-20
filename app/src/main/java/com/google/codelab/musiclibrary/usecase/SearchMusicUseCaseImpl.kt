package com.google.codelab.musiclibrary.usecase

import com.google.codelab.musiclibrary.model.SearchResponse
import com.google.codelab.musiclibrary.repository.SearchMusicRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchMusicUseCaseImpl : SearchMusicUseCase {
    private val repository = SearchMusicRepositoryImpl()

    override fun fetchMusic(keyword: String, offset: Int): Single<SearchResponse> {
        return repository.fetchMusic(keyword, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.body()
            }
    }
}
