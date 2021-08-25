package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : IsUserLoggedUseCase {

    override operator fun invoke() = repository.isUserLogged()
}