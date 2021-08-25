package com.maciel.murillo.auth.domain.model

object UserInfoFactory {

    fun makeUserInfo(
        id: String = "id",
        name: String = "name",
        email: String = "email",
        phone: String = "phone",
        imageUrl: String = "imageUrl",
    ) = UserInfo(id, name, email, phone, imageUrl)
}