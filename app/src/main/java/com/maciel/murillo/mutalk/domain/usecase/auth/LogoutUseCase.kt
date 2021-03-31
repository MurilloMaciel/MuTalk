package com.maciel.murillo.mutalk.domain.usecase.auth

import com.maciel.murillo.mutalk.domain.repository.AuthRepository

class LogoutUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke() {
        repository.logout()
//        repository.deleteUserUid()
    }
}