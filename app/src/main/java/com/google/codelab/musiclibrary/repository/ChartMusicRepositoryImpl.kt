package com.google.codelab.musiclibrary.repository

import com.google.codelab.musiclibrary.App
import com.google.codelab.musiclibrary.data.RemoteData
import com.google.codelab.musiclibrary.model.businessmodel.LocalMapper
import com.google.codelab.musiclibrary.model.businessmodel.ResponseMapper
import com.google.codelab.musiclibrary.model.businessmodel.Tracks
import com.google.codelab.musiclibrary.model.local.TracksEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class ChartMusicRepositoryImpl @Inject constructor(
    private val remote: RemoteData
) : ChartMusicRepository {
    private val dao = App.database.tracksDao()
    private val tracks: PublishSubject<List<Tracks>> = PublishSubject.create()

    override fun fetchChartMusic(startPage: Int){
        Single.fromCallable { dao.loadTracks() }
            .subscribeOn(Schedulers.io())
            .subscribeBy {
                if (it.isEmpty()) {
                    fetchRemote(startPage)
                } else {
                    fetchLocal()
                }
            }
    }

    override fun getTracksStream(): Observable<List<Tracks>> = tracks.hide()

    private fun fetchRemote(startPage: Int) {
        remote.fetchChartMusic(startPage)
            .map { ResponseMapper.transform(it) }
            .subscribeBy {
                tracks.onNext(it)
                it.mapIndexed { index, tracks ->
                    dao.saveTracks(
                        TracksEntity(
                            id = index,
                            title = tracks.title,
                            subtitle = tracks.subtitle,
                            images = tracks.images,
                            url = tracks.url
                        )
                    )
                }
            }
    }

    private fun fetchLocal() {
        Single.fromCallable { dao.loadTracks() }
            .subscribeBy {
                tracks.onNext(LocalMapper.transform(it))
            }
    }
}
