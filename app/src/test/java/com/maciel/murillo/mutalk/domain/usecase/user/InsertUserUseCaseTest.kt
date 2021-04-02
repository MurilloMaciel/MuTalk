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

class InsertUserUseCaseTest {

    private val repository: UserRepository = mockk(relaxed = true)

    private lateinit var insertUserUseCase: InsertUserUseCase

    @Before
    fun setUp() {
        insertUserUseCase = InsertUserUseCase(repository)
    }

    @Test
    fun `should insert user from repository`() = runBlocking {
        val user = User(
            id = "id",
            name = "name",
            imageUrl = "imageUrl",
            email = "email",
        )

        val result = insertUserUseCase.invoke(user)

        coVerify { repository.insertUser(user) }
        confirmVerified(repository)
        assertEquals(Unit, result)
    }
}