package com.maciel.murillo.mutalk.domain.model

data class ContactChat(
    override var id: String,
    override var members: List<User>,
    override var messages: List<Message>,
) : Chat()