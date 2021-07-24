package com.pinakin.giffresh.data.remote

import com.pinakin.giffresh.data.remote.model.TrendingGif
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifFreshApi {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = "mRlz9pzysR8FCkH8f3RXX4or7x2kTUU7",
        @Query("offset") page: Int = 0,
        @Query("limit") size: Int = 20
    ): Response<TrendingGif>

    @GET("search")
    suspend fun search(
        @Query("api_key") apiKey: String = "mRlz9pzysR8FCkH8f3RXX4or7x2kTUU7",
        @Query("q") query: String,
        @Query("offset") page: Int = 0,
        @Query("limit") size: Int = 20
    ): Response<TrendingGif>
}