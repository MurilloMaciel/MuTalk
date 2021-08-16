package com.maciel.murillo.gif_finder.data.datasource

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.util.result.Result

interface GifFinderRemoteDataSource {

    suspend fun getTrendingGifs(): Result<List<Gif>, GetGifsError>

    suspend fun search(query: String): Result<List<Gif>, GetGifsError>
}