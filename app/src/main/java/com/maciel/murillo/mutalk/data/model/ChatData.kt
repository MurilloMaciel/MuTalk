package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.Chat

data class ChatData(
    val idSender: String,
    val idReceiver: String,
    val lastMessage: String,
    val isGroup: String,
    val displayedUser: UserData,
    val group: GroupData,
)

fun ChatData.mapToChat() = Chat(
    idSender = this.idSender,
    idReceiver = this.idReceiver,
    lastMessage = this.lastMessage,
    isGroup = this.isGroup,
    displayedUser = this.displayedUser.mapToUser(),
    group = this.group.mapToGroup(),
)

fun Chat.mapToChatData() = ChatData(
    idSender = this.idSender,
    idReceiver = this.idReceiver,
    lastMessage = this.lastMessage,
    isGroup = this.isGroup,
    displayedUser = this.displayedUser.mapToUserData(),
    group = this.group.mapToGroupData(),
)