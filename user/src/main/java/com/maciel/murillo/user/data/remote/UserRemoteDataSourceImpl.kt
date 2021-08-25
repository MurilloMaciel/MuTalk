package com.maciel.murillo.user.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.maciel.murillo.extensions.forEachNotNull
import com.maciel.murillo.extensions.safe
import com.maciel.murillo.user.data.datasource.UserRemoteDataSource
import com.maciel.murillo.user.data.mapper.UserDataToModelMapper
import com.maciel.murillo.user.data.mapper.UserModelToUserDataMapper
import com.maciel.murillo.user.data.model.PROPERTY_NAME
import com.maciel.murillo.user.data.model.UserData
import com.maciel.murillo.user.domain.model.*
import com.maciel.murillo.util.error.CrudError
import com.maciel.murillo.util.result.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val COLLECTION_USER = "user"
private const val PARSING_ERROR = "Error parsing user"

class UserRemoteDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val userDataToModelMapper: UserDataToModelMapper,
    private val userModelToUserDataMapper: UserModelToUserDataMapper,
) : UserRemoteDataSource {

    private val collection by lazy { db.collection(COLLECTION_USER) }

    override suspend fun insert(user: User): Result<User, CrudError.Insert> {
        return try {
            collection.document().run {
                user.copy(id = id).let { userToSave ->
                    set(userModelToUserDataMapper.mapFrom(userToSave))
                    Result.Success(userToSave)
                }
            }
        } catch (e: Exception) {
            Result.Error(UserInsertError(e.message.safe(), COLLECTION_USER))
        }
    }

    override suspend fun get(id: String): Result<User, CrudError.Get> {
        return try {
            collection.document(id).get().await().run {
                    toObject<UserData>()?.let { userData ->
                        Result.Success(userDataToModelMapper.mapFrom(userData))
                    }
                }
            Result.Error(UserGetError(PARSING_ERROR, COLLECTION_USER))
        } catch (e: Exception) {
            Result.Error(UserGetError(e.message.safe(), COLLECTION_USER))
        }
    }

    override suspend fun update(user: User): Result<User, CrudError.Update> {
        return try {
            collection.document(user.id).set(user).await()
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(UserUpdateError(e.message.safe(), COLLECTION_USER))
        }
    }

    override suspend fun delete(user: User): Result<User, CrudError.Delete> {
        return try {
            collection.document(user.id).delete().await()
            Result.Success(user)
        } catch (e: Exception) {
            Result.Error(UserDeleteError(e.message.safe(), COLLECTION_USER))
        }
    }

    override suspend fun getAll(): Result<List<User>, CrudError.GetAll> {
        return try {
            val users = mutableListOf<User>()
            collection.get().await().documents.forEachNotNull { document ->
                document.toObject<UserData>()?.let { userData ->
                    users.add(userDataToModelMapper.mapFrom(userData))
                }
            }
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(UserGetAllError(e.message.safe(), COLLECTION_USER))
        }
    }

    override suspend fun getByName(filterName: String): Result<List<User>, CrudError.Filter> {
        return try {
            val users = mutableListOf<User>()
            collection.whereEqualTo(PROPERTY_NAME, filterName)
                .get()
                .await()
                .documents
                .forEachNotNull { document ->
                    document.toObject<UserData>()?.let { userData ->
                        users.add(userDataToModelMapper.mapFrom(userData))
                    }
                }
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(UserGetByNameError(e.message.safe(), COLLECTION_USER))
        }
    }
}