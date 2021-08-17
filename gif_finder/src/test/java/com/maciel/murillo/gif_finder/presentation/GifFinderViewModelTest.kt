package com.maciel.murillo.gif_finder.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maciel.murillo.gif_finder.domain.usecase.GetTrendingGifsUseCase
import com.maciel.murillo.gif_finder.domain.usecase.SearchGifsUseCase
import com.maciel.murillo.test_util.CoroutineTestRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GifFinderViewModelTest {

    @get:Rule
    internal val instantTask = InstantTaskExecutorRule()

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val getTrendingGifsUseCase: GetTrendingGifsUseCase = mockk()
    private val searchGifsUseCase: SearchGifsUseCase = mockk()
    private val viewModel: GifFinderViewModel = GifFinderViewModel(
        dispatcherProvider = coroutineTestRule.testDispatcherProvider,
        getTrendingGifsUseCase = getTrendingGifsUseCase,
        searchGifsUseCase = searchGifsUseCase,
    )

//    @Test
//    fun test() = coroutineTestRule.runBlockingTest {
//
//    }
}