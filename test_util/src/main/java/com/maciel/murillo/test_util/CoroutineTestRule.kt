package com.maciel.murillo.test_util

import com.maciel.murillo.util.provider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.ContinuationInterceptor

/**
 * The reference to use this and dispatcher provider is here
 * https://craigrussell.io/2019/11/unit-testing-coroutine-suspend-functions-using-testcoroutinedispatcher/
 * */

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {
    val testDispatcher = coroutineContext[ContinuationInterceptor] as TestCoroutineDispatcher

    val testDispatcherProvider = object : DispatcherProvider {
        override fun default(): CoroutineDispatcher = testDispatcher
        override fun io(): CoroutineDispatcher = testDispatcher
        override fun main(): CoroutineDispatcher = testDispatcher
        override fun unconfined(): CoroutineDispatcher = testDispatcher
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
