package com.maciel.murillo.mutalk.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.maciel.murillo.mutalk.data.datasource.UserRemoteDataSource
import com.maciel.murillo.mutalk.data.model.UserData
import kotlinx.coroutines.tasks.await

private const val COLLECTION_USER = "user"
const val PROPERTY_NAME = "username"

class UserRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : UserRemoteDataSource {

    private val collection by lazy { db.collection(COLLECTION_USER) }

    override suspend fun insertUser(user: UserData) {
        collection.document().let { document ->
            document.set(user.apply {
                id = document.id
            }).await()
        }
    }

    override suspend fun getUser(id: String): UserData {
        val snapshot = collection.document(id).get().await()
        return snapshot.toObject<UserData>() ?: throw Exception()
    }

    override suspend fun updatetUser(user: UserData) {
        collection.document(user.id).set(user).await()
    }

    override suspend fun deleteUser(user: UserData) {
        collection.document(user.id).delete().await()
    }

    override suspend fun getUsersByName(filterName: String): List<UserData> {
        val snapshot = collection
            .whereEqualTo(PROPERTY_NAME, filterName)
            .get()
            .await()
        val users = mutableListOf<UserData>()
        snapshot.documents.forEach { document ->
            document.toObject<UserData>()?.run {
                users.add(this).apply { id = document.id }
            }
        }
        return users
    }

    override suspend fun getAllUsers(): List<UserData> {
        val snapshot = collection.get().await()
        val users = mutableListOf<UserData>()
        snapshot.documents.forEach { document ->
            document.toObject<UserData>()?.run {
                users.add(this).apply { id = document.id }
            }
        }
        return users
    }
}