package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUserUseCase {

    override suspend operator fun invoke(id: String) = repository.get(id)
}