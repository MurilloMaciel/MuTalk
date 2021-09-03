package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.model.UserFactory
import com.maciel.murillo.user.domain.model.UserGetError
import com.maciel.murillo.user.domain.repository.UserRepository
import com.maciel.murillo.util.error.CrudError
import com.maciel.murillo.util.result.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetUserUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: UserRepository = mockk()
    private val useCase: GetUserUseCase = GetUserUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnCrudGetError() = coroutineTestRule.runBlockingTest {
        val userGetResult = Result.Error(
            UserGetError(collection = "collection", msg = "msg")
        )
        prepareScenario(userGetResult = userGetResult)

        val result = useCase("id")

        assertTrue(result is Result.Error)
        assertEquals(userGetResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userGetResult = Result.Success(user)
        prepareScenario(userGetResult = userGetResult)

        val result = useCase("id")

        assertTrue(result is Result.Success)
        assertEquals(userGetResult.value, result.get())
    }

    private fun prepareScenario(
        userGetResult: Result<User, CrudError.Get>? = null,
    ) {
        userGetResult?.let { coEvery { repository.get(any()) } returns it }
    }
}