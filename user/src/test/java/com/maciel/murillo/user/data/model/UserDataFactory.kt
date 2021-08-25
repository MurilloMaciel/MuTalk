package com.maciel.murillo.user.data.model

object UserDataFactory {

    fun makeUserData(
        id: String = "id",
        email: String = "email",
        imageUrl: String = "imageUrl",
        name: String = "name",
    ) = UserData(id, email, imageUrl, name)
}