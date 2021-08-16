package com.maciel.murillo.mutalk.data.repository

import com.maciel.murillo.util.extensions.safe
import com.maciel.murillo.mutalk.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.mutalk.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override fun isUserLogged() = authRemoteDataSource.isUserLogged()

    override suspend fun signup(email: String, password: String) =
        authRemoteDataSource.signup(email, password)?.uid.safe()

    override suspend fun login(email: String, password: String) =
        authRemoteDataSource.login(email, password)?.uid.safe()

    override suspend fun logout() = authRemoteDataSource.logout()
}