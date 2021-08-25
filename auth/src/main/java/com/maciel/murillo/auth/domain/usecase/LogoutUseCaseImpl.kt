package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LogoutUseCase {

    override suspend operator fun invoke() = repository.logout()
}