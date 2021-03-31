package com.maciel.murillo.mutalk.domain.usecase.auth

import com.maciel.murillo.mutalk.domain.repository.AuthRepository

class IsUserLoggedUseCase(private val repository: AuthRepository) {

    operator fun invoke() = repository.isUserLogged()
}