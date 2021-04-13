package com.maciel.murillo.mutalk.domain.model

data class Message(
    val id: String,
    val senderId: String,
    val message: String,
    val imageUrl: String,
)