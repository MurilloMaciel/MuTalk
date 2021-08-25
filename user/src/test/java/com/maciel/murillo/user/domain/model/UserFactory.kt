package com.maciel.murillo.user.domain.model

object UserFactory {

    fun makeUser(
        id: String = "id",
        email: String = "email",
        imageUrl: String = "imageUrl",
        name: String = "name",
    ) = User(id, email, imageUrl, name)
}