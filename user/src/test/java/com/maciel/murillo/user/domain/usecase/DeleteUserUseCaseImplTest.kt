package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.model.UserDeleteError
import com.maciel.murillo.user.domain.model.UserFactory
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
class DeleteUserUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: UserRepository = mockk()
    private val useCase: DeleteUserUseCase = DeleteUserUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnCrudDeleteError() = coroutineTestRule.runBlockingTest {
        val userDeleteResult = Result.Error(
            UserDeleteError(collection = "collection", msg = "msg")
        )
        prepareScenario(userDeleteResult = userDeleteResult)

        val result = useCase(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userDeleteResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userDeleteResult = Result.Success(user)
        prepareScenario(userDeleteResult = userDeleteResult)

        val result = useCase(user)

        assertTrue(result is Result.Success)
        assertEquals(userDeleteResult.value, result.get())
    }

    private fun prepareScenario(
        userDeleteResult: Result<User, CrudError.Delete>? = null,
    ) {
        userDeleteResult?.let { coEvery { repository.delete(any()) } returns it }
    }
}