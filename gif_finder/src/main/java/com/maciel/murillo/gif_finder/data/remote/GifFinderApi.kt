package com.maciel.murillo.gif_finder.data.remote

import com.maciel.murillo.gif_finder.data.model.GifResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GifFinderApi {

    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String,
        @Query("offset") offset: Int
    ): Response<GifResponse>

    @GET("search")
    suspend fun search(
        @Query("q") query: String,
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String,
        @Query("offset") offset: Int
    ): Response<GifResponse>
}