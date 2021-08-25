package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.util.result.Result

interface SignupUseCase {
    suspend operator fun invoke(email: String, password: String): Result<UserInfo, AuthError>
}