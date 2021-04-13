package com.maciel.murillo.mutalk.domain.model

data class Group(
    override var id: String,
    override var members: List<User>,
    override var messages: List<Message>,
    val name: String,
    val imageUrl: String,
) : Chat()