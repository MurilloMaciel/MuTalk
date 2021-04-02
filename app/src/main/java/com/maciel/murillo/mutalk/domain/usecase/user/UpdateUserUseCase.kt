package com.maciel.murillo.mutalk.domain.usecase.user

import com.maciel.murillo.mutalk.domain.model.User
import com.maciel.murillo.mutalk.domain.repository.UserRepository

class UpdateUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(user: User) = repository.updatetUser(user)
}