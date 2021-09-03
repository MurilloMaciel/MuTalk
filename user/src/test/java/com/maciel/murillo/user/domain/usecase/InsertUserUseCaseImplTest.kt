package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.model.UserFactory
import com.maciel.murillo.user.domain.model.UserInsertError
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
class InsertUserUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: UserRepository = mockk()
    private val useCase: InsertUserUseCase = InsertUserUseCaseImpl(repository)

    @Test
    fun invoke_repositoryReturnError_returnCrudInsertError() = coroutineTestRule.runBlockingTest {
        val userInsertResult = Result.Error(
            UserInsertError(collection = "collection", msg = "msg")
        )
        prepareScenario(userInsertResult = userInsertResult)

        val result = useCase(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userInsertResult.value, result.getError())
    }

    @Test
    fun invoke_repositoryReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userInsertResult = Result.Success(user)
        prepareScenario(userInsertResult = userInsertResult)

        val result = useCase(user)

        assertTrue(result is Result.Success)
        assertEquals(userInsertResult.value, result.get())
    }

    private fun prepareScenario(
        userInsertResult: Result<User, CrudError.Insert>? = null,
    ) {
        userInsertResult?.let { coEvery { repository.insert(any()) } returns it }
    }
}