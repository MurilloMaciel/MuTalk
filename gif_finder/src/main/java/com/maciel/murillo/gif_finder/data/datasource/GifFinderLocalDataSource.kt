package com.maciel.murillo.gif_finder.data.datasource

import com.maciel.murillo.gif_finder.domain.model.Gif

interface GifFinderLocalDataSource {

    suspend fun saveTrendingGifsIntoCache(gifs: List<Gif>)

    suspend fun readTrendingGifsFromCache(): List<Gif>?
}