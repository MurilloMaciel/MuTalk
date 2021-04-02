package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.User

data class UserData(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var imageUrl: String = "",
)

fun UserData.mapToUser() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    imageUrl = this.imageUrl,
)

fun User.mapToUserData() = UserData(
    id = this.id,
    name = this.name,
    email = this.email,
    imageUrl = this.imageUrl,
)