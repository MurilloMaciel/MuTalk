package com.maciel.murillo.mutalk.domain.repository

import com.maciel.murillo.mutalk.domain.model.User

interface UserRepository {

    suspend fun insertUser(user: User)

    suspend fun getUser(uid: String): User

    suspend fun updatetUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUsersByName(filterName: String): List<User>

    suspend fun getAllUsers(): List<User>
}