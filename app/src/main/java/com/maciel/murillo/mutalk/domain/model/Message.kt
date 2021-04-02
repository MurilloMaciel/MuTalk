package com.maciel.murillo.mutalk.domain.model

data class Message(
    val userId: String,
    val message: String,
    val image: String,
    val senderName: String,
)