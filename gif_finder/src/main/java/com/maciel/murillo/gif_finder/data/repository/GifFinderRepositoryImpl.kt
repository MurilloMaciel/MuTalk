package com.maciel.murillo.gif_finder.data.repository

import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.datasource.GifFinderRemoteDataSource
import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import com.maciel.murillo.util.result.Result
import javax.inject.Inject

class GifFinderRepositoryImpl @Inject constructor(
    private val remoteDataSource: GifFinderRemoteDataSource,
    private val localDataSource: GifFinderLocalDataSource,
) : GifFinderRepository {

    override suspend fun getTrendingGifs(): Result<List<Gif>, GetGifsError> {
        return localDataSource.readTrendingGifsFromCache().let { cache ->
            if (cache == null) {
                remoteDataSource.getTrendingGifs().mapError { it }.mapSuccess { gifs ->
                    gifs.also { localDataSource.saveTrendingGifsIntoCache(it) }
                }
            } else {
                Result.Success(cache)
            }
        }
    }

    override suspend fun search(query: String): Result<List<Gif>, GetGifsError> {
        return remoteDataSource.search(query)
    }
}