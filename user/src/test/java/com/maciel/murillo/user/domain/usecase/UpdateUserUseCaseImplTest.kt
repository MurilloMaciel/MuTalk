package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.model.UserFactory
import com.maciel.murillo.user.domain.model.UserUpdateError
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
class UpdateUserUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: UserRepository = mockk()
    private val useCase: UpdateUserUseCase = UpdateUserUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnCrudUpdateError() = coroutineTestRule.runBlockingTest {
        val userUpdateResult = Result.Error(
            UserUpdateError(collection = "collection", msg = "msg")
        )
        prepareScenario(userUpdateResult = userUpdateResult)

        val result = useCase(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userUpdateResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userUpdateResult = Result.Success(user)
        prepareScenario(userUpdateResult = userUpdateResult)

        val result = useCase(user)

        assertTrue(result is Result.Success)
        assertEquals(userUpdateResult.value, result.get())
    }

    private fun prepareScenario(
        userUpdateResult: Result<User, CrudError.Update>? = null,
    ) {
        userUpdateResult?.let { coEvery { repository.update(any()) } returns it }
    }
}