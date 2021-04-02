package com.maciel.murillo.mutalk.data.datasource

import com.maciel.murillo.mutalk.data.model.UserData

interface UserRemoteDataSource {

    suspend fun insertUser(user: UserData)

    suspend fun getUser(id: String): UserData

    suspend fun updatetUser(user: UserData)

    suspend fun deleteUser(user: UserData)

    suspend fun getUsersByName(filterName: String): List<UserData>

    suspend fun getAllUsers(): List<UserData>
}