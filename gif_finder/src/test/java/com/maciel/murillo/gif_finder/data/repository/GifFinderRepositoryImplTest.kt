package com.maciel.murillo.gif_finder.data.repository

import com.maciel.murillo.gif_finder.data.datasource.GifFinderLocalDataSource
import com.maciel.murillo.gif_finder.data.datasource.GifFinderRemoteDataSource
import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

private const val QUERY: String = "query"

@ExperimentalCoroutinesApi
class GifFinderRepositoryImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val remoteDataSource: GifFinderRemoteDataSource = mockk()
    private val localDataSource: GifFinderLocalDataSource = mockk()
    private val repository: GifFinderRepository = GifFinderRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
    )

    @Test
    fun search_callRemoteDataSource() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        repository.search(query = QUERY)

        coVerify { remoteDataSource.search(query = QUERY) }
        confirmVerified(remoteDataSource)
    }

    @Test
    fun search_success_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = repository.search(query = QUERY)

        assertEquals(Result.Success(emptyList()), result)
    }

    @Test
    fun search_error_returnError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(GetGifsError.EmptyResult)
        prepareScenario(gifsRemoteResult = error)

        val result = repository.search(query = QUERY)

        assertEquals(error, result)
    }

    @Test
    fun getTrendingGifs_callLocalDataSource() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        repository.getTrendingGifs()

        coVerify { localDataSource.readTrendingGifsFromCache() }
    }

    @Test
    fun getTrendingGifs_validCache_dontCallRemoteDataSource() = coroutineTestRule.runBlockingTest {
        val gifs = listOf(GifFactory.makeGif())
        prepareScenario(gifsLocalResult = gifs)

        repository.getTrendingGifs()

        coVerify(exactly = 0) { remoteDataSource.getTrendingGifs() }
    }

    @Test
    fun getTrendingGifs_validCache_returnSuccess() = coroutineTestRule.runBlockingTest {
        val gifs = listOf(GifFactory.makeGif())
        prepareScenario(gifsLocalResult = gifs)

        val result = repository.getTrendingGifs()

        assertEquals(Result.Success(gifs), result)
    }

    @Test
    fun getTrendingGifs_invalidCache_callRemoteDataSource() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        repository.getTrendingGifs()

        coVerify { remoteDataSource.getTrendingGifs() }
    }

    @Test
    fun getTrendingGifs_invalidCacheAndRemoteError_returnError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(GetGifsError.EmptyResult)
        prepareScenario(gifsRemoteResult = error)

        val result = repository.getTrendingGifs()

        assertEquals(error, result)
    }

    @Test
    fun getTrendingGifs_invalidCacheAndRemoteSuccess_saveCache() = coroutineTestRule.runBlockingTest {
        val gifs = listOf(GifFactory.makeGif())
        prepareScenario(gifsRemoteResult = Result.Success(gifs))

        repository.getTrendingGifs()

        coVerify { localDataSource.saveTrendingGifsIntoCache(gifs) }
    }

    @Test
    fun getTrendingGifs_invalidCacheAndRemoteSuccess_returnGifs() = coroutineTestRule.runBlockingTest {
        val gifs = listOf(GifFactory.makeGif())
        prepareScenario(gifsRemoteResult = Result.Success(gifs))

        val result = repository.getTrendingGifs()

        assertEquals(Result.Success(gifs), result)
    }

    private fun prepareScenario(
        gifsLocalResult: List<Gif>? = null,
        gifsRemoteResult: Result<List<Gif>, GetGifsError> = Result.Success(emptyList())
    ) {
        coEvery { localDataSource.saveTrendingGifsIntoCache(gifs = any()) } just runs
        coEvery { localDataSource.readTrendingGifsFromCache() } returns gifsLocalResult
        coEvery { remoteDataSource.getTrendingGifs() } returns gifsRemoteResult
        coEvery { remoteDataSource.search(query = QUERY) } returns gifsRemoteResult
    }
}