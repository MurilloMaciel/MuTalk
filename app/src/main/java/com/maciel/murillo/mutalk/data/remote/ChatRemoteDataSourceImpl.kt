package com.maciel.murillo.mutalk.data.remote

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.maciel.murillo.mutalk.data.datasource.ChatRemoteDataSource
import com.maciel.murillo.mutalk.data.model.ChatData
import com.maciel.murillo.mutalk.data.model.MessageData
import kotlinx.coroutines.tasks.await

private const val COLLECTION_CHAT = "chat"
const val PROPERTY_MEMBERS = "members"
const val PROPERTY_MESSAGES = "messages"

class ChatRemoteDataSourceImpl(
    private val db: FirebaseFirestore
) : ChatRemoteDataSource {

    private val collection by lazy { db.collection(COLLECTION_CHAT) }

    override suspend fun insert(chat: ChatData) {
        collection.document().let { document ->
            document.set(chat.apply {
                id = document.id
            }).await()
        }
    }

    override suspend fun delete(chat: ChatData) {
        collection.document(chat.id).delete().await()
    }

    override suspend fun update(chat: ChatData) {
        collection.document(chat.id).set(chat).await()
    }

    override suspend fun getChat(id: String): ChatData {
        val snapshot = collection.document(id).get().await()
        return snapshot.toObject<ChatData>() ?: throw Exception()
    }

    override suspend fun getAllByUser(userId: String): List<ChatData> {
        val snapshot = collection
            .whereArrayContains(PROPERTY_MEMBERS, userId)
            .get()
            .await()
        val chats = mutableListOf<ChatData>()
        snapshot.documents.forEach { document ->
            document.toObject<ChatData>()?.run {
                chats.add(this).apply { id = document.id }
            }
        }
        return chats
    }

    override suspend fun insertMessage(chat: ChatData, messageData: MessageData) {
        collection.document(chat.id)
            .update(PROPERTY_MESSAGES, FieldValue.arrayUnion(messageData))
            .await()
    }

    override suspend fun deleteMessage(chat: ChatData, messageData: MessageData) {
        collection.document(chat.id)
            .update(PROPERTY_MESSAGES, FieldValue.arrayRemove(messageData))
            .await()
    }
}