package com.maciel.murillo.mutalk.data.repository

import com.maciel.murillo.mutalk.data.datasource.UserRemoteDataSource
import com.maciel.murillo.mutalk.data.model.mapToUser
import com.maciel.murillo.mutalk.data.model.mapToUserData
import com.maciel.murillo.mutalk.domain.model.User
import com.maciel.murillo.mutalk.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun insertUser(user: User) =
        userRemoteDataSource.insertUser(user.mapToUserData())

    override suspend fun getUser(uid: String) =
        userRemoteDataSource.getUser(uid).mapToUser()

    override suspend fun updatetUser(user: User) =
        userRemoteDataSource.updatetUser(user.mapToUserData())

    override suspend fun deleteUser(user: User) =
        userRemoteDataSource.deleteUser(user.mapToUserData())

    override suspend fun getUsersByName(filterName: String) =
        userRemoteDataSource.getUsersByName(filterName).map { it.mapToUser() }

    override suspend fun getAllUsers() =
        userRemoteDataSource.getAllUsers().map { it.mapToUser() }
}