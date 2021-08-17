package com.maciel.murillo.gif_finder.data.remote

import com.maciel.murillo.gif_finder.data.datasource.GifFinderRemoteDataSource
import com.maciel.murillo.gif_finder.data.mapper.GifResponseToModelMapper
import com.maciel.murillo.gif_finder.data.model.GifResponse
import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.model.GifResponseFactory
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GifFinderRemoteDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val api: GifFinderApi = mockk()
    private val gifResponseToModelMapper = GifResponseToModelMapper()
    private val dataSource: GifFinderRemoteDataSource = GifFinderRemoteDataSourceImpl(
        api = api,
        gifResponseToModelMapper = gifResponseToModelMapper,
    )

    @Test
    fun getTrendingGifs_error_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario(gifResponse = null)

        val result = dataSource.getTrendingGifs()

        assertTrue(result is Result.Error)
    }

    @Test
    fun getTrendingGifs_error_returnGenericError() = coroutineTestRule.runBlockingTest {
        prepareScenario(gifResponse = null)

        lateinit var result: GetGifsError
        dataSource.getTrendingGifs().onError {
            result = it
        }

        assertEquals(GetGifsError.Generic, result)
    }

    @Test
    fun getTrendingGifs_success_returnSuccess() = coroutineTestRule.runBlockingTest {
        val gifResponse = GifResponseFactory.makeGifResponse()
        prepareScenario(gifResponse = gifResponse)

        val result = dataSource.getTrendingGifs()

        assertTrue(result is Result.Success)
    }

    @Test
    fun getTrendingGifs_success_returnGifs() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.getTrendingGifs().handleResult()

        val gifs = listOf(GifFactory.makeGif())
        assertEquals(gifs, result)
    }

    @Test
    fun search_error_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario(gifResponse = null)

        val result = dataSource.search(query = "query")

        assertTrue(result is Result.Error)
    }

    @Test
    fun search_error_returnGifError() = coroutineTestRule.runBlockingTest {
        prepareScenario(gifResponse = null)

        lateinit var result: GetGifsError
        dataSource.search(query = "query").onError {
            result = it
        }

        assertEquals(GetGifsError.Generic, result)
    }

    @Test
    fun search_success_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.search(query = "query")

        assertTrue(result is Result.Success)
    }

    @Test
    fun search_success_returnGifs() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.search(query = "query").handleResult()

        val gifs = listOf(GifFactory.makeGif())
        assertEquals(gifs, result)
    }

    private fun prepareScenario(
        gifResponse: GifResponse? = GifResponseFactory.makeGifResponse()
    ) {
        coEvery { api.getTrendingGifs(any(), any(), any(), any()) } returns Response.success(gifResponse)
        coEvery { api.search(any(), any(), any(), any(), any()) } returns Response.success(gifResponse)
    }
}