package com.maciel.murillo.mutalk.domain.usecase.auth

import com.maciel.murillo.mutalk.domain.repository.AuthRepository

class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String) {
        val userUid = repository.login(email, password)
//        repository.saveUserUid(userUid)
    }
}