package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.Chat
import com.maciel.murillo.mutalk.domain.model.ContactChat
import com.maciel.murillo.mutalk.domain.model.Group
import java.lang.Exception

abstract class ChatData {
    abstract var id: String
    abstract var members: List<UserData>
    abstract var messages: List<MessageData>
}

fun ChatData.mapToChat(): Chat = when (this) {
    is ContactChatData -> ContactChat(
        id = this.id,
        members = this.members.map { it.mapToUser() },
        messages = this.messages.map { it.mapToMessage() },
    )
    is GroupData -> Group(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        members = this.members.map { it.mapToUser() },
        messages = this.messages.map { it.mapToMessage() },
    )
    else -> throw Exception("map to chat error")
}

fun Chat.mapToChatData(): ChatData = when (this) {
    is ContactChat -> ContactChatData(
        id = this.id,
        members = this.members.map { it.mapToUserData() },
        messages = this.messages.map { it.mapToMessageData() },
    )
    is Group -> GroupData(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        members = this.members.map { it.mapToUserData() },
        messages = this.messages.map { it.mapToMessageData() },
    )
    else -> throw Exception("map to chat data error")
}