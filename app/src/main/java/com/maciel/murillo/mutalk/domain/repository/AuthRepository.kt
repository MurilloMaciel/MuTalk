package com.maciel.murillo.mutalk.domain.repository

interface AuthRepository {

    fun isUserLogged(): Boolean

    suspend fun signup(email: String, password: String): String

    suspend fun login(email: String, password: String): String

    suspend fun logout()
}