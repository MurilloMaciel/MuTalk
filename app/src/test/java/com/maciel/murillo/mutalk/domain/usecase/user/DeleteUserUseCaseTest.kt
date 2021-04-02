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

class DeleteUserUseCaseTest {

    private val repository: UserRepository = mockk(relaxed = true)

    private lateinit var deleteUserUseCase: DeleteUserUseCase

    @Before
    fun setUp() {
        deleteUserUseCase = DeleteUserUseCase(repository)
    }

    @Test
    fun `should delete user from repository`() = runBlocking {
        val user = User(
            id = "id",
            name = "name",
            imageUrl = "imageUrl",
            email = "email",
        )

        val result = deleteUserUseCase.invoke(user)

        coVerify { repository.deleteUser(user) }
        confirmVerified(repository)
        assertEquals(Unit, result)
    }
}