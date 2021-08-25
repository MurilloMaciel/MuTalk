package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LoginUseCase{

    override suspend operator fun invoke(email: String, password: String) =
        repository.login(email, password)
}