package com.maciel.murillo.mutalk.domain.usecase.user

import com.maciel.murillo.mutalk.domain.model.User
import com.maciel.murillo.mutalk.domain.repository.UserRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UpdateUserUseCaseTest {

    private val repository: UserRepository = mockk(relaxed = true)

    private lateinit var updateUserUseCase: UpdateUserUseCase

    @Before
    fun setUp() {
        updateUserUseCase = UpdateUserUseCase(repository)
    }

    @Test
    fun `should update user from repository`() = runBlocking {
        val user = User(
            id = "id",
            name = "name",
            imageUrl = "imageUrl",
            email = "email",
        )

        val result = updateUserUseCase.invoke(user)

        coVerify { repository.updatetUser(user) }
        confirmVerified(repository)
        assertEquals(Unit, result)
    }
}