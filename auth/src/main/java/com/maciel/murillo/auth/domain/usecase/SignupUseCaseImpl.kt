package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : SignupUseCase {

    override suspend operator fun invoke(email: String, password: String) =
        repository.signup(email, password)
}