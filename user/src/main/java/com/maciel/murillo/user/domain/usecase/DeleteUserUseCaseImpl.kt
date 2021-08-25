package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : DeleteUserUseCase {

    override suspend operator fun invoke(user: User) = repository.delete(user)
}