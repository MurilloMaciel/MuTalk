package com.maciel.murillo.gif_finder.domain.usecase

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetTrendingGifsUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: GifFinderRepository = mockk()
    private val getTrendingGifsUseCase: GetTrendingGifsUseCase = GetTrendingGifsUseCaseImpl(repository)

    @Test
    fun invoke_callRepository() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        getTrendingGifsUseCase()

        coVerify { repository.getTrendingGifs() }
        confirmVerified(repository)
    }

    @Test
    fun invoke_success_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = getTrendingGifsUseCase()

        assertEquals(Result.Success(emptyList()), result)
    }

    @Test
    fun invoke_error_returnError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(GetGifsError.Generic)
        prepareScenario(trendingGifsResult = error)

        val result = getTrendingGifsUseCase()

        assertEquals(error, result)
    }

    private fun prepareScenario(
        trendingGifsResult: Result<List<Gif>, GetGifsError> = Result.Success(emptyList())
    ) {
        coEvery { repository.getTrendingGifs() } returns trendingGifsResult
    }
}