package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.repository.AuthRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LogoutUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: AuthRepository = mockk()
    private val useCase: LogoutUseCase = LogoutUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnAuthError() = coroutineTestRule.runBlockingTest {
        val logoutResult = Result.Error(
            AuthError.Logout(msg = "msg")
        )
        prepareScenario(logoutResult = logoutResult)

        val result = useCase()

        assertTrue(result is Result.Error)
        assertEquals(logoutResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnSuccess() = coroutineTestRule.runBlockingTest {
        val logoutResult = Result.Success(Unit)
        prepareScenario(logoutResult = logoutResult)

        val result = useCase()

        assertTrue(result is Result.Success)
        assertEquals(logoutResult.value, result.get())
    }

    private fun prepareScenario(
        logoutResult: Result<Unit, AuthError> = Result.Success(Unit),
    ) {
        coEvery { repository.logout() } returns logoutResult
    }
}