package com.maciel.murillo.gif_finder.data.local

import androidx.datastore.core.DataStore
import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.mapper.GifListProtoToGifsMapper
import com.maciel.murillo.gif_finder.data.mapper.GifsToGifListProtoMapper
import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.model.GifProtoFactory
import com.maciel.murillo.test_util.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GifFinderLocalDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val gifListProtoToGifsMapper = GifListProtoToGifsMapper()
    private val gifsToGifListProtoMapper = GifsToGifListProtoMapper()
    private val dataStore: DataStore<GifListProto> = mockk()
    private val dataSource: GifFinderLocalDataSource = GifFinderLocalDataSourceImpl(
        dataStore = dataStore,
        gifsToGifListProtoMapper = gifsToGifListProtoMapper,
        gifListProtoToGifsMapper = gifListProtoToGifsMapper,
    )

    @Test
    fun readTrendingGifsFromCache_cacheValid_returnGifs() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.readTrendingGifsFromCache()

        val expected = listOf(GifFactory.makeGif())

        assertEquals(expected, result)
    }

    @Test
    fun readTrendingGifsFromCache_cacheEmpty_returnNull() = coroutineTestRule.runBlockingTest {
        prepareScenario(
            gifListProto = GifProtoFactory.makeGifListProto(gifs = emptyList())
        )

        val result = dataSource.readTrendingGifsFromCache()

        assertNull(result)
    }

    @Test
    fun readTrendingGifsFromCache_invalidTimestamp_returnNull() = coroutineTestRule.runBlockingTest {
        prepareScenario(
            gifListProto = GifProtoFactory.makeGifListProto(timestamp = Date().time - CACHE_TTL - 1)
        )

        val result = dataSource.readTrendingGifsFromCache()

        assertNull(result)
    }

    @Test
    fun saveTrendingGifsIntoCache_updateTimestamp() = coroutineTestRule.runBlockingTest {
        val gifs = listOf(GifFactory.makeGif())
        lateinit var actual: GifListProto
        prepareSavingCacheScenario(
            onExecute = { actual = it }
        )

        val before = Date().time
        dataSource.saveTrendingGifsIntoCache(gifs)
        val after = Date().time

        assertTrue(actual.timestamp > before && actual.timestamp < after)
    }

    private fun prepareScenario(
        gifListProto: GifListProto = GifProtoFactory.makeGifListProto()
    ) {
        coEvery { dataStore.data } returns flowOf(gifListProto)
    }

    private fun prepareSavingCacheScenario(
        cacheProto: GifListProto = GifProtoFactory.makeGifListProto(),
        onExecute: (GifListProto) -> Unit,
    ) {
        val slot = slot<suspend (GifListProto) -> GifListProto>()
        coEvery {
            dataStore.updateData(capture(slot))
        } coAnswers {
            slot.captured(cacheProto).apply {
                onExecute(this)
            }
        }
    }
}