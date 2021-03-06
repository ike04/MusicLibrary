package com.google.codelab.musiclibrary.data

import com.google.codelab.musiclibrary.BuildConfig
import com.google.codelab.musiclibrary.model.ArtistTracks
import com.google.codelab.musiclibrary.model.ChartResponse
import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    @Headers(
        BuildConfig.API_KEY,
        "x-rapidapi-host: shazam.p.rapidapi.com",
        "useQueryString: true"
    )
    @GET("search")
    fun fetchSearchResult(
        @Query("term") term: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<Response<SearchResponse>>

    @Headers(
        BuildConfig.API_KEY,
        "x-rapidapi-host: shazam.p.rapidapi.com",
        "useQueryString: true"
    )
    @GET("charts/track")
    fun fetchCharts(
        @Query("listId") listId: String,
        @Query("pageSize") pageSize: Int,
        @Query("startFrom") startFrom: Int
    ): Single<Response<ChartResponse>>

    @Headers(
        BuildConfig.API_KEY,
        "x-rapidapi-host: shazam.p.rapidapi.com",
        "useQueryString: true"
    )
    @GET("songs/list-artist-top-tracks")
    fun fetchArtistTracks(
        @Query("id") artistId: String
    ): Single<Response<ArtistTracks>>
}
