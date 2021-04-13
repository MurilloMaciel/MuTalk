package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.Message

data class MessageData(
    var id: String = "",
    var senderId: String = "",
    var message: String = "",
    var imageUrl: String = "",
)

fun MessageData.mapToMessage() = Message(
    id = this.id,
    senderId = this.senderId,
    message = this.message,
    imageUrl = this.imageUrl,
)

fun Message.mapToMessageData() = MessageData(
    id = this.id,
    senderId = this.senderId,
    message = this.message,
    imageUrl = this.imageUrl,
)