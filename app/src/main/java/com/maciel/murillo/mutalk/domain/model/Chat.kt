package com.maciel.murillo.mutalk.domain.model

data class Chat(
    val idSender: String,
    val idReceiver: String,
    val lastMessage: String,
    val isGroup: String,
    val displayedUser: User,
    val group: Group,
)