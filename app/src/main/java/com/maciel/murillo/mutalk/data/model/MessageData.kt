package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.Message

data class MessageData(
    val userId: String = "",
    val message: String = "",
    val image: String = "",
    val senderName: String = "",
)

fun MessageData.mapToMessage() = Message(
    userId = this.userId,
    message = this.message,
    image = this.image,
    senderName = this.senderName,
)

fun Message.mapToMessageData() = MessageData(
    userId = this.userId,
    message = this.message,
    image = this.image,
    senderName = this.senderName,
)