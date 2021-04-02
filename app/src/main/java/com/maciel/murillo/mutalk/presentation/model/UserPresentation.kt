package com.maciel.murillo.mutalk.presentation.model

import com.maciel.murillo.mutalk.domain.model.User

data class UserPresentation(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: String = "",
)

fun UserPresentation.mapToUser() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    imageUrl = this.image,
)

fun User.mapToUserPresentation() = UserPresentation(
    id = this.id,
    name = this.name,
    email = this.email,
    image = this.imageUrl,
)