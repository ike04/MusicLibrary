package com.google.codelab.musiclibrary.data

import com.google.codelab.musiclibrary.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    @Headers(
        "x-rapidapi-key: c2aabedfb3msh84de69484f23decp1cc625jsn8ae661131e47",
        "x-rapidapi-host: shazam.p.rapidapi.com"
    )
    @GET("{search}")
    fun fetchSearchResult(
        @Path("search") tagId: String,
        @Query("term") term: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Single<Response<List<SearchResponse>>>
}
