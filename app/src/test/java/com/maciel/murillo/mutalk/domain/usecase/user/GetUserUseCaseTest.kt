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

class GetUserUseCaseTest {

    private val repository: UserRepository = mockk(relaxed = true)

    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        getUserUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `should get user from an id from repository`() = runBlocking {
        val id = "id"
        val user = User(
            id = id,
            name = "name",
            imageUrl = "imageUrl",
            email = "email",
        )
        coEvery { repository.getUser(id) } returns user

        val result = getUserUseCase.invoke(id)

        coVerify { repository.getUser(id) }
        confirmVerified(repository)
        assertEquals(user, result)
    }
}