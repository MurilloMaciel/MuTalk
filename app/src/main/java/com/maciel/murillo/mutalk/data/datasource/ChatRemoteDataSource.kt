package com.maciel.murillo.mutalk.data.datasource

import com.maciel.murillo.mutalk.data.model.ChatData
import com.maciel.murillo.mutalk.data.model.MessageData

interface ChatRemoteDataSource {

    suspend fun insert(chat: ChatData)

    suspend fun delete(chat: ChatData)

    suspend fun update(chat: ChatData)

    suspend fun getChat(id: String): ChatData

    suspend fun getAllByUser(userId: String): List<ChatData>

    suspend fun insertMessage(chat: ChatData, messageData: MessageData)

    suspend fun deleteMessage(chat: ChatData, messageData: MessageData)
}