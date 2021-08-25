package com.maciel.murillo.auth.domain.usecase

import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.util.result.Result

interface LogoutUseCase {
    suspend operator fun invoke(): Result<Unit, AuthError>
}