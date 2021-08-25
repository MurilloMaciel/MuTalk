package com.maciel.murillo.auth.data.repository

import com.maciel.murillo.auth.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
) : AuthRepository {

    override fun isUserLogged() = authRemoteDataSource.isUserLogged()

    override suspend fun logout() = authRemoteDataSource.logout()

    override suspend fun signup(email: String, password: String) =
        authRemoteDataSource.signup(email, password)

    override suspend fun login(email: String, password: String) =
        authRemoteDataSource.login(email, password)
}