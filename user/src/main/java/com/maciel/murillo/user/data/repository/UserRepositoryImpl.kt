package com.maciel.murillo.user.data.repository

import com.maciel.murillo.user.data.datasource.UserRemoteDataSource
import com.maciel.murillo.user.data.mapper.UserDataToModelMapper
import com.maciel.murillo.user.data.mapper.UserModelToUserDataMapper
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun insert(user: User) = userRemoteDataSource.insert(user)

    override suspend fun get(uid: String) = userRemoteDataSource.get(uid)

    override suspend fun update(user: User) = userRemoteDataSource.update(user)

    override suspend fun delete(user: User) = userRemoteDataSource.delete(user)

    override suspend fun getAll() = userRemoteDataSource.getAll()

    override suspend fun getByName(filterName: String) = userRemoteDataSource.getByName(filterName)
}