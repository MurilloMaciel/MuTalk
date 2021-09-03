package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.repository.AuthRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse

@ExperimentalCoroutinesApi
class IsUserLoggedUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: AuthRepository = mockk()
    private val useCase: IsUserLoggedUseCase = IsUserLoggedUseCaseImpl(repository)

    @Test
    fun invoke_currentUserIsNull() = coroutineTestRule.runBlockingTest {
        val userIsLogged = false
        prepareScenario(isUserLogged = userIsLogged)

        val result = useCase()

        assertFalse(result)
    }

    @Test
    fun invoke_currentUserIsntNull() = coroutineTestRule.runBlockingTest {
        val userIsLogged = true
        prepareScenario(isUserLogged = userIsLogged)

        val result = useCase()

        Assert.assertTrue(result)
    }

    private fun prepareScenario(
        isUserLogged: Boolean = true,
    ) {
        every { repository.isUserLogged() } returns isUserLogged
    }
}