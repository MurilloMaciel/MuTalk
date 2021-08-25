package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UpdateUserUseCase {

    override suspend operator fun invoke(user: User) = repository.update(user)
}