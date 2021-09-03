package com.maciel.murillo.user.data.repository

import com.maciel.murillo.test_util.CoroutineTestRule
import com.maciel.murillo.user.data.datasource.UserRemoteDataSource
import com.maciel.murillo.user.domain.model.*
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
class UserRepositoryImplTest {

    @get:Rule
    internal val coroutineTestRule = CoroutineTestRule()

    private val userRemoteDataSource: UserRemoteDataSource = mockk()
    private val repository: UserRepository = UserRepositoryImpl(
        userRemoteDataSource = userRemoteDataSource
    )

    @Test
    fun insert_dataSourceReturnError_returnCrudInsertError() = coroutineTestRule.runBlockingTest {
        val userInsertResult = Result.Error(
            UserInsertError(collection = "collection", msg = "msg")
        )
        prepareScenario(userInsertResult = userInsertResult)

        val result = repository.insert(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userInsertResult.value, result.getError())
    }

    @Test
    fun insert_dataSourceReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userInsertResult = Result.Success(user)
        prepareScenario(userInsertResult = userInsertResult)

        val result = repository.insert(user)

        assertTrue(result is Result.Success)
        assertEquals(userInsertResult.value, result.get())
    }

    @Test
    fun get_dataSourceReturnError_returnCrudGetError() = coroutineTestRule.runBlockingTest {
        val userGetResult = Result.Error(
            UserGetError(collection = "collection", msg = "msg")
        )
        prepareScenario(userGetResult = userGetResult)

        val result = repository.get("id")

        assertTrue(result is Result.Error)
        assertEquals(userGetResult.value, result.getError())
    }

    @Test
    fun get_dataSourceReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userGetResult = Result.Success(user)
        prepareScenario(userGetResult = userGetResult)

        val result = repository.get("id")

        assertTrue(result is Result.Success)
        assertEquals(userGetResult.value, result.get())
    }

    @Test
    fun update_dataSourceReturnError_returnCrudUpdateError() = coroutineTestRule.runBlockingTest {
        val userUpdateResult = Result.Error(
            UserUpdateError(collection = "collection", msg = "msg")
        )
        prepareScenario(userUpdateResult = userUpdateResult)

        val result = repository.update(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userUpdateResult.value, result.getError())
    }

    @Test
    fun update_dataSourceReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userUpdateResult = Result.Success(user)
        prepareScenario(userUpdateResult = userUpdateResult)

        val result = repository.update(user)

        assertTrue(result is Result.Success)
        assertEquals(userUpdateResult.value, result.get())
    }

    @Test
    fun delete_dataSourceReturnError_returnCrudDeleteError() = coroutineTestRule.runBlockingTest {
        val userDeleteResult = Result.Error(
            UserDeleteError(collection = "collection", msg = "msg")
        )
        prepareScenario(userDeleteResult = userDeleteResult)

        val result = repository.delete(UserFactory.makeUser())

        assertTrue(result is Result.Error)
        assertEquals(userDeleteResult.value, result.getError())
    }

    @Test
    fun delete_dataSourceReturnSuccess_returnUserWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userDeleteResult = Result.Success(user)
        prepareScenario(userDeleteResult = userDeleteResult)

        val result = repository.delete(user)

        assertTrue(result is Result.Success)
        assertEquals(userDeleteResult.value, result.get())
    }

    @Test
    fun getAll_dataSourceReturnError_returnCrudGetAllError() = coroutineTestRule.runBlockingTest {
        val userListGetAllResult = Result.Error(
            UserGetAllError(collection = "collection", msg = "msg")
        )
        prepareScenario(userListGetAllResult = userListGetAllResult)

        val result = repository.getAll()

        assertTrue(result is Result.Error)
        assertEquals(userListGetAllResult.value, result.getError())
    }

    @Test
    fun getAll_dataSourceReturnSuccess_returnUserListWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userListGetAllResult = Result.Success(listOf(user))
        prepareScenario(userListGetAllResult = userListGetAllResult)

        val result = repository.getAll()

        assertTrue(result is Result.Success)
        assertEquals(userListGetAllResult.value, result.get())
    }

    @Test
    fun getByName_dataSourceReturnError_returnCrudFilterError() = coroutineTestRule.runBlockingTest {
        val userListFilterResult = Result.Error(
            UserGetByNameError(collection = "collection", msg = "msg")
        )
        prepareScenario(userListFilterResult = userListFilterResult)

        val result = repository.getByName("name")

        assertTrue(result is Result.Error)
        assertEquals(userListFilterResult.value, result.getError())
    }

    @Test
    fun getByName_dataSourceReturnSuccess_returnUserListWithSuccess() = coroutineTestRule.runBlockingTest {
        val user = UserFactory.makeUser()
        val userListFilterResult = Result.Success(listOf(user))
        prepareScenario(userListFilterResult = userListFilterResult)

        val result = repository.getByName("name")

        assertTrue(result is Result.Success)
        assertEquals(userListFilterResult.value, result.get())
    }

    private fun prepareScenario(
        userInsertResult: Result<User, CrudError.Insert>? = null,
        userDeleteResult: Result<User, CrudError.Delete>? = null,
        userUpdateResult: Result<User, CrudError.Update>? = null,
        userGetResult: Result<User, CrudError.Get>? = null,
        userListGetAllResult: Result<List<User>, CrudError.GetAll>? = null,
        userListFilterResult: Result<List<User>, CrudError.Filter>? = null,
    ) {
        userInsertResult?.let { coEvery { userRemoteDataSource.insert(any()) } returns it }
        userGetResult?.let { coEvery { userRemoteDataSource.get(any()) } returns it }
        userUpdateResult?.let { coEvery { userRemoteDataSource.update(any()) } returns it }
        userDeleteResult?.let { coEvery { userRemoteDataSource.delete(any()) } returns it }
        userListFilterResult?.let { coEvery { userRemoteDataSource.getByName(any()) } returns it }
        userListGetAllResult?.let { coEvery { userRemoteDataSource.getAll() } returns it }
    }
}