package com.maciel.murillo.auth.domain.repository

import com.maciel.murillo.auth.domain.model.AuthError
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.util.result.Result

interface AuthRepository {

    fun isUserLogged(): Boolean

    suspend fun signup(email: String, password: String): Result<UserInfo, AuthError>

    suspend fun login(email: String, password: String): Result<UserInfo, AuthError>

    suspend fun logout(): Result<Unit, AuthError>
}