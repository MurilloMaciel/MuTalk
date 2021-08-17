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

private const val QUERY: String = "query"

@ExperimentalCoroutinesApi
class SearchGifsUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: GifFinderRepository = mockk()
    private val searchGifsUseCase: SearchGifsUseCase = SearchGifsUseCaseImpl(repository)

    @Test
    fun invoke_callRepository() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        searchGifsUseCase(query = QUERY)

        coVerify { repository.search(query = QUERY) }
        confirmVerified(repository)
    }

    @Test
    fun invoke_success_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = searchGifsUseCase(query = QUERY)

        kotlin.test.assertEquals(Result.Success(emptyList()), result)
    }

    @Test
    fun invoke_error_returnError() = coroutineTestRule.runBlockingTest {
        val error = Result.Error(GetGifsError.Generic)
        prepareScenario(trendingGifsResult = error)

        val result = searchGifsUseCase(query = QUERY)

        kotlin.test.assertEquals(error, result)
    }

    private fun prepareScenario(
        query: String = QUERY,
        trendingGifsResult: Result<List<Gif>, GetGifsError> = Result.Success(emptyList())
    ) {
        coEvery { repository.search(query = QUERY) } returns trendingGifsResult
    }
}