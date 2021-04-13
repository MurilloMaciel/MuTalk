package com.maciel.murillo.mutalk.domain.model

abstract class Chat {
    abstract var id: String
    abstract var members: List<User>
    abstract var messages: List<Message>
}