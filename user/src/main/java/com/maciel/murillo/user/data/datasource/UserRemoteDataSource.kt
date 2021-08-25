package com.maciel.murillo.user.data.datasource

import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.util.error.CrudError
import com.maciel.murillo.util.result.Result

interface UserRemoteDataSource {

    suspend fun insert(user: User): Result<User, CrudError.Insert>

    suspend fun get(id: String): Result<User, CrudError.Get>

    suspend fun update(user: User): Result<User, CrudError.Update>

    suspend fun delete(user: User): Result<User, CrudError.Delete>

    suspend fun getAll(): Result<List<User>, CrudError.GetAll>

    suspend fun getByName(filterName: String): Result<List<User>, CrudError.Filter>
}