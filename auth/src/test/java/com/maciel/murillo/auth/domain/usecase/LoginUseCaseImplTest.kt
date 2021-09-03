package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.auth.domain.model.UserInfoFactory
import com.maciel.murillo.auth.domain.repository.AuthRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LoginUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: AuthRepository = mockk()
    private val useCase: LoginUseCase = LoginUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnAuthError() = coroutineTestRule.runBlockingTest {
        val authResult = Result.Error(
            AuthError.Login(msg = "msg")
        )
        prepareScenario(authResult = authResult)

        val result = useCase("email", "password")

        Assert.assertTrue(result is Result.Error)
        assertEquals(authResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnUserInfoWithSuccess() = coroutineTestRule.runBlockingTest {
        val userInfo = UserInfoFactory.makeUserInfo()
        val authResult = Result.Success(userInfo)
        prepareScenario(authResult = authResult)

        val result = useCase("email", "password")

        Assert.assertTrue(result is Result.Success)
        assertEquals(authResult.value, result.get())
    }

    private fun prepareScenario(
        authResult: Result<UserInfo, AuthError> = Result.Success(UserInfoFactory.makeUserInfo()),
    ) {
        coEvery { repository.login(any(), any()) } returns authResult
    }
}