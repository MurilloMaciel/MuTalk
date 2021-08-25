package com.maciel.murillo.auth.domain.usecase

interface IsUserLoggedUseCase {
    operator fun invoke(): Boolean
}