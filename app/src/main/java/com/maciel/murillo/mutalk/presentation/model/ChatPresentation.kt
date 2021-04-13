//package com.maciel.murillo.mutalk.presentation.model
//
//import com.maciel.murillo.mutalk.domain.model.Chat
//
//data class ChatPresentation(
//    val idSender: String,
//    val idReceiver: String,
//    val lastMessage: String,
//    val isGroup: String,
//    val displayedUser: UserPresentation,
//    val group: GroupPresentation,
//)
//
//fun ChatPresentation.mapToChat() = Chat(
//    idSender = this.idSender,
//    idReceiver = this.idReceiver,
//    lastMessage = this.lastMessage,
//    isGroup = this.isGroup,
//    displayedUser = this.displayedUser.mapToUser(),
//    group = this.group.mapToGroup(),
//)
//
//fun Chat.mapToChatPresentation() = ChatPresentation(
//    idSender = this.idSender,
//    idReceiver = this.idReceiver,
//    lastMessage = this.lastMessage,
//    isGroup = this.isGroup,
//    displayedUser = this.displayedUser.mapToUserPresentation(),
//    group = this.group.mapToGroupPresentation(),
//)