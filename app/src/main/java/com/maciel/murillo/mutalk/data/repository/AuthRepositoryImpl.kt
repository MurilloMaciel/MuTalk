package com.maciel.murillo.mutalk.data.repository

import com.maciel.murillo.mutalk.core.extensions.safe
import com.maciel.murillo.mutalk.data.datasource.AuthDataSource
import com.maciel.murillo.mutalk.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authDataSource: AuthDataSource,
) : AuthRepository {

    override fun isUserLogged() = authDataSource.isUserLogged()

    override suspend fun signup(email: String, password: String) =
        authDataSource.signup(email, password)?.uid.safe()

    override suspend fun login(email: String, password: String) =
        authDataSource.login(email, password)?.uid.safe()

    override suspend fun logout() = authDataSource.logout()
}