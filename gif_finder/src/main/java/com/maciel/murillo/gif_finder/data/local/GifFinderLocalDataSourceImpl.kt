package com.maciel.murillo.gif_finder.data.local

import androidx.datastore.core.DataStore
import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.mapper.GifListProtoToGifsMapper
import com.maciel.murillo.gif_finder.data.mapper.GifsToGifListProtoMapper
import com.maciel.murillo.gif_finder.domain.model.Gif
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val CACHE_TTL = 3600000 // 1 HOUR OF TIME TO LIVE

class GifFinderLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<GifListProto>,
    private val gifListProtoToGifsMapper: GifListProtoToGifsMapper,
    private val gifsToGifListProtoMapper: GifsToGifListProtoMapper
) : GifFinderLocalDataSource {

    override suspend fun readTrendingGifsFromCache(): List<Gif>? {
        return try {
            dataStore.data.first().let {
                if (isCacheValid(it)) {
                    gifListProtoToGifsMapper.mapFrom(dataStore.data.first())
                } else {
                    null
                }
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun saveTrendingGifsIntoCache(gifs: List<Gif>) {
        try {
            dataStore.updateData {
                gifsToGifListProtoMapper.mapFrom(gifs).copy(
                    timestamp = System.currentTimeMillis()
                )
            }
        } catch (e: Exception) {
            // do nothing
        }
    }

    private fun isCacheValid(gifsList: GifListProto): Boolean {
        return gifsList.gifs.isEmpty().not().and(
            System.currentTimeMillis() - gifsList.timestamp <= CACHE_TTL
        )
    }
}