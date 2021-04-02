package com.maciel.murillo.mutalk.presentation.model

import com.maciel.murillo.mutalk.domain.model.Message

data class MessagePresentation(
    val userId: String = "",
    val message: String = "",
    val image: String = "",
    val senderName: String = "",
)

fun MessagePresentation.mapToMessage() = Message(
    userId = this.userId,
    message = this.message,
    image = this.image,
    senderName = this.senderName,
)

fun Message.mapToMessagePresentation() = MessagePresentation(
    userId = this.userId,
    message = this.message,
    image = this.image,
    senderName = this.senderName,
)