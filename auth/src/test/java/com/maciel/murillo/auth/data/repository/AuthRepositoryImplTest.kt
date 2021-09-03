package com.maciel.murillo.auth.data.repository

import com.maciel.murillo.auth.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.auth.domain.model.UserInfoFactory
import com.maciel.murillo.auth.domain.repository.AuthRepository
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val dataSource: AuthRemoteDataSource = mockk()
    private val repository: AuthRepository = AuthRepositoryImpl(dataSource)

    @Test
    fun isUserLogged_currentUserIsNull() = coroutineTestRule.runBlockingTest {
        val userIsLogged = false
        prepareScenario(isUserLogged = userIsLogged)

        val result = repository.isUserLogged()

        assertFalse(result)
    }

    @Test
    fun isUserLogged_currentUserIsntNull() = coroutineTestRule.runBlockingTest {
        val userIsLogged = true
        prepareScenario(isUserLogged = userIsLogged)

        val result = repository.isUserLogged()

        assertTrue(result)
    }

    @Test
    fun logout_dataSourceReturnError_returnAuthError() = coroutineTestRule.runBlockingTest {
        val logoutResult = Result.Error(
            AuthError.Logout(msg = "msg")
        )
        prepareScenario(logoutResult = logoutResult)

        val result = repository.logout()

        assertTrue(result is Result.Error)
        assertEquals(logoutResult.value, result.getError())
    }

    @Test
    fun logout_dataSourceReturnSuccess_returnSuccess() = coroutineTestRule.runBlockingTest {
        val logoutResult = Result.Success(Unit)
        prepareScenario(logoutResult = logoutResult)

        val result = repository.logout()

        assertTrue(result is Result.Success)
        assertEquals(logoutResult.value, result.get())
    }

    @Test
    fun login_dataSourceReturnError_returnAuthError() = coroutineTestRule.runBlockingTest {
        val authResult = Result.Error(
            AuthError.Login(msg = "msg")
        )
        prepareScenario(authResult = authResult)

        val result = repository.login("email", "password")

        assertTrue(result is Result.Error)
        assertEquals(authResult.value, result.getError())
    }

    @Test
    fun login_dataSourceReturnSuccess_returnUserInfoWithSuccess() = coroutineTestRule.runBlockingTest {
        val userInfo = UserInfoFactory.makeUserInfo()
        val authResult = Result.Success(userInfo)
        prepareScenario(authResult = authResult)

        val result = repository.login("email", "password")

        assertTrue(result is Result.Success)
        assertEquals(authResult.value, result.get())
    }

    @Test
    fun signup_dataSourceReturnError_returnAuthError() = coroutineTestRule.runBlockingTest {
        val authResult = Result.Error(
            AuthError.Signup(msg = "msg")
        )
        prepareScenario(authResult = authResult)

        val result = repository.signup("email", "password")

        assertTrue(result is Result.Error)
        assertEquals(authResult.value, result.getError())
    }

    @Test
    fun signup_dataSourceReturnSuccess_returnUserInfoWithSuccess() = coroutineTestRule.runBlockingTest {
        val userInfo = UserInfoFactory.makeUserInfo()
        val authResult = Result.Success(userInfo)
        prepareScenario(authResult = authResult)

        val result = repository.signup("email", "password")

        assertTrue(result is Result.Success)
        assertEquals(authResult.value, result.get())
    }

    private fun prepareScenario(
        authResult: Result<UserInfo, AuthError> = Result.Success(UserInfoFactory.makeUserInfo()),
        logoutResult: Result<Unit, AuthError> = Result.Success(Unit),
        isUserLogged: Boolean = true,
    ) {
        every { dataSource.isUserLogged() } returns isUserLogged
        coEvery { dataSource.logout() } returns logoutResult
        coEvery { dataSource.signup(any(), any()) } returns authResult
        coEvery { dataSource.login(any(), any()) } returns authResult
    }
}