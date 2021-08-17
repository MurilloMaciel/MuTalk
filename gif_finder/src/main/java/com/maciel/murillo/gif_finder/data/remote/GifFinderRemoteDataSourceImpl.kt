package com.maciel.murillo.gif_finder.data.remote

import android.util.Log
import com.maciel.murillo.gif_finder.data.datasource.GifFinderRemoteDataSource
import com.maciel.murillo.gif_finder.data.mapper.GifResponseToModelMapper
import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.network.model.HttpStatus
import com.maciel.murillo.network.network.safeCall
import com.maciel.murillo.util.result.Result
import javax.inject.Inject

private const val API_KEY = "c2EM0FVdDNef4kyV95G6GHGsz2AVcz4f"
private const val LIMIT = 20
private const val OFFSET = 0
private const val MOST_ACCEPTED_RATING = "g"
private const val ACCEPTED_RATING = "pg"
private const val STRANGE_RATING = "pg-13"

class GifFinderRemoteDataSourceImpl @Inject constructor(
    private val api: GifFinderApi,
    private val gifResponseToModelMapper: GifResponseToModelMapper,
) : GifFinderRemoteDataSource {

    override suspend fun getTrendingGifs(): Result<List<Gif>, GetGifsError> {
        return api.getTrendingGifs(
            apiKey = API_KEY,
            limit = LIMIT,
            rating = MOST_ACCEPTED_RATING,
            offset = OFFSET,
        ).safeCall().mapSuccess {
//            Log.d("Murillo", "trendingGifs -> $it")
            gifResponseToModelMapper.mapFrom(it)
        }.mapError {
            GetGifsError.Generic
        }
    }

    override suspend fun search(query: String): Result<List<Gif>, GetGifsError> {
        return api.search(
            query = query,
            apiKey = API_KEY,
            limit = LIMIT,
            rating = MOST_ACCEPTED_RATING,
            offset = OFFSET,
        ).safeCall().mapSuccess {
            gifResponseToModelMapper.mapFrom(it)
        }.mapError {
            mapSearchGifsError(it)
        }
    }

    private fun mapSearchGifsError(httpStatus: HttpStatus): GetGifsError {
        return if (httpStatus == HttpStatus.NOT_FOUND) {
            GetGifsError.EmptyResult
        } else {
            GetGifsError.Generic
        }
    }
}