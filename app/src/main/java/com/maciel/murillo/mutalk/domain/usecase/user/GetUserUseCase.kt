package com.maciel.murillo.mutalk.domain.usecase.user

import com.maciel.murillo.mutalk.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(id: String) = repository.getUser(id)
}