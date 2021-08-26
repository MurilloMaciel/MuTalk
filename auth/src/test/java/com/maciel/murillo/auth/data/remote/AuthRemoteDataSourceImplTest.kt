package com.maciel.murillo.auth.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.maciel.murillo.auth.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.auth.data.mapper.FirebaseUserToModelMapper
import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.auth.domain.model.UserInfoFactory
import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class AuthRemoteDataSourceImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val email = "email"
    private val password = "password"

    private val task: Task<AuthResult> = mockk()
    private val authResult: AuthResult = mockk()
    private val firebaseUser: FirebaseUser = mockk(relaxed = true)

    private val firebaseUserToModelMapper: FirebaseUserToModelMapper = mockk()
    private val auth: FirebaseAuth = mockk()
    private val dataSource: AuthRemoteDataSource = AuthRemoteDataSourceImpl(
        firebaseUserToModelMapper = firebaseUserToModelMapper,
        auth = auth,
    )

    @Test
    fun isUserLogged_firebaseUserNull_returnFalse() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.isUserLogged()

        assertFalse(result)
    }

    @Test
    fun isUserLogged_firebaseUserIsFilled_returnTrue() = coroutineTestRule.runBlockingTest {
        prepareScenario(firebaseUser = firebaseUser)

        val result = dataSource.isUserLogged()

        assertTrue(result)
    }

    @Test
    fun logout_exceptionCaught_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.logout()

        assertTrue(result is Result.Error)
        assertTrue(result.getError() is AuthError.Logout)
    }

    @Test
    fun logout_runWithoutExceptions_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.logout()

        assertTrue(result is Result.Success)
    }

    @Test
    fun signup_exceptionCaught_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.signup(email, password)

        assertTrue(result is Result.Error)
        assertTrue(result.getError() is AuthError.Signup)
    }

    @Test
    fun signup_firebaseUserNull_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.signup(email, password)

        assertTrue(result is Result.Error)
        assertTrue(result.getError() is AuthError.Signup)
    }

    @Test
    fun signup_runWithoutExceptions_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario(firebaseUser = firebaseUser)

        val result = dataSource.signup(email, password)

        assertTrue(result is Result.Success)
    }

    @Test
    fun login_exceptionCaught_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario(throwException = true)

        val result = dataSource.login(email, password)

        assertTrue(result is Result.Error)
        assertTrue(result.getError() is AuthError.Login)
    }

    @Test
    fun login_firebaseUserNull_returnError() = coroutineTestRule.runBlockingTest {
        prepareScenario()

        val result = dataSource.login(email, password)

        assertTrue(result is Result.Error)
        assertTrue(result.getError() is AuthError.Login)
    }

    @Test
    fun login_runWithoutExceptions_returnSuccess() = coroutineTestRule.runBlockingTest {
        prepareScenario(firebaseUser = firebaseUser)

        val result = dataSource.login(email, password)

        assertTrue(result is Result.Success)
    }

    private fun prepareScenario(
        throwException: Boolean = false,
        firebaseUser: FirebaseUser? = null
    ) {
        if (throwException) {
            coEvery { auth.signOut() } throws Exception("message")
            coEvery { auth.createUserWithEmailAndPassword(any(), any()) } throws Exception("message")
            coEvery { auth.signInWithEmailAndPassword(any(), any()) } throws Exception("message")
        } else {
            coEvery { firebaseUserToModelMapper.mapFrom(any()) } returns UserInfoFactory.makeUserInfo()
            coEvery { auth.currentUser } returns firebaseUser
            coEvery { auth.signOut() } just runs
            coEvery { auth.createUserWithEmailAndPassword(any(), any()) } returns task
            coEvery { auth.signInWithEmailAndPassword(any(), any()) } returns task
            coEvery { task.exception } returns null
            coEvery { task.isCanceled } returns false
            coEvery { task.isComplete } returns true
            coEvery { task.result } returns authResult
            coEvery { authResult.user } returns firebaseUser
        }
    }
}