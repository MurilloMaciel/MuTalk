package com.maciel.murillo.gif_finder.data.local

import androidx.datastore.core.DataStore
import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.mapper.GifListProtoToGifsMapper
import com.maciel.murillo.gif_finder.data.mapper.GifsToGifListProtoMapper
import com.maciel.murillo.gif_finder.domain.model.Gif
import kotlinx.coroutines.flow.first
import java.io.IOException
import java.util.*
import javax.inject.Inject

const val CACHE_TTL = 3600000L // 1 HOUR OF TIME TO LIVE

class GifFinderLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<GifListProto>,
    private val gifListProtoToGifsMapper: GifListProtoToGifsMapper,
    private val gifsToGifListProtoMapper: GifsToGifListProtoMapper
) : GifFinderLocalDataSource {

    override suspend fun readTrendingGifsFromCache(): List<Gif>? {
        return try {
            dataStore.data.first().let { gifsProto ->
                if (isCacheValid(gifsProto)) {
                    gifListProtoToGifsMapper.mapFrom(gifsProto)
                } else {
                    null
                }
            }
        } catch (e: IOException) {
            null
        }
    }

    override suspend fun saveTrendingGifsIntoCache(gifs: List<Gif>) {
        try {
            dataStore.updateData {
                gifsToGifListProtoMapper.mapFrom(gifs).copy(
                    timestamp = Date().time
                )
            }
        } catch (e: IOException) {
            // do nothing
        }
    }

    private fun isCacheValid(gifsList: GifListProto): Boolean {
        return gifsList.gifs
            .isEmpty()
            .not()
            .and(Date().time - gifsList.timestamp <= CACHE_TTL)
    }
}