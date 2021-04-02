package com.maciel.murillo.mutalk.domain.usecase.user

import com.maciel.murillo.mutalk.domain.model.User
import com.maciel.murillo.mutalk.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUsersByNameUseCaseTest {

    private val repository: UserRepository = mockk(relaxed = true)

    private lateinit var getUsersByNameUseCase: GetUsersByNameUseCase

    @Before
    fun setUp() {
        getUsersByNameUseCase = GetUsersByNameUseCase(repository)
    }

    @Test
    fun `should get all users from repository if filter is null`() = runBlocking {
        val filterName: String? = null
        val users = emptyList<User>()
        coEvery { repository.getAllUsers() } returns users

        val result = getUsersByNameUseCase.invoke(filterName)

        coVerify { repository.getAllUsers() }
        coVerify(exactly = 0) { repository.getUsersByName(any()) }
        confirmVerified(repository)
        assertEquals(users, result)
    }

    @Test
    fun `should get all users from repository if filter is blank`() = runBlocking {
        val filterName: String? = ""
        val users = emptyList<User>()
        coEvery { repository.getAllUsers() } returns users

        val result = getUsersByNameUseCase.invoke(filterName)

        coVerify { repository.getAllUsers() }
        coVerify(exactly = 0) { repository.getUsersByName(any()) }
        confirmVerified(repository)
        assertEquals(users, result)
    }

    @Test
    fun `should get users by name filter from repository if filter isnt blank`() = runBlocking {
        val filterName = "name"
        val users = emptyList<User>()
        coEvery { repository.getUsersByName(filterName) } returns users

        val result = getUsersByNameUseCase.invoke(filterName)

        coVerify { repository.getUsersByName(filterName) }
        coVerify(exactly = 0) { repository.getAllUsers() }
        confirmVerified(repository)
        assertEquals(users, result)
    }
}