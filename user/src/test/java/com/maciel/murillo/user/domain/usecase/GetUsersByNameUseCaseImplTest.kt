package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.model.UserFactory
import com.maciel.murillo.user.domain.model.UserGetAllError
import com.maciel.murillo.user.domain.model.UserGetByNameError
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
class GetUsersByNameUseCaseImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val repository: UserRepository = mockk()
    private val useCase: GetUsersByNameUseCase = GetUsersByNameUseCaseImpl(repository)

    @Test
    fun invoke_validNameAndRepositoryReturnError_returnCrudFilterError() = coroutineTestRule.runBlockingTest {
        val userListFilterResult = Result.Error(
            UserGetByNameError(collection = "collection", msg = "msg")
        )
        prepareScenario(userListFilterResult = userListFilterResult)

        val result = useCase("name")

        assertTrue(result is Result.Error)
        assertEquals(userListFilterResult.value, result.getError())
    }

    @Test
    fun invoke_validNameAndRepositoryReturnSuccess_returnUserListWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userListFilterResult = Result.Success(listOf(user))
        prepareScenario(userListFilterResult = userListFilterResult)

        val result = useCase("name")

        assertTrue(result is Result.Success)
        assertEquals(userListFilterResult.value, result.get())
    }

    @Test
    fun invoke_nullNameAndRepositoryReturnError_returnCrudFilterError() = coroutineTestRule.runBlockingTest {
        val userListGetAllResult = Result.Error(
            UserGetAllError(collection = "collection", msg = "msg")
        )
        prepareScenario(userListGetAllResult = userListGetAllResult)

        val result = useCase(filterName = null)

        assertTrue(result is Result.Error)
        assertEquals(userListGetAllResult.value, result.getError())
    }

    @Test
    fun invoke_nullNameAndRepositoryReturnSuccess_returnUserListWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userListGetAllResult = Result.Success(listOf(user))
        prepareScenario(userListGetAllResult = userListGetAllResult)

        val result = useCase(filterName = null)

        assertTrue(result is Result.Success)
        assertEquals(userListGetAllResult.value, result.get())
    }

    private fun prepareScenario(
        userListFilterResult: Result<List<User>, CrudError.Filter>? = null,
        userListGetAllResult: Result<List<User>, CrudError.GetAll>? = null,
    ) {
        userListFilterResult?.let { coEvery { repository.getByName(any()) } returns it }
        userListGetAllResult?.let { coEvery { repository.getAll() } returns it }
    }
}