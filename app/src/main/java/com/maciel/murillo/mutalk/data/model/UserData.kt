package com.maciel.murillo.mutalk.data.model

import com.google.firebase.firestore.PropertyName
import com.maciel.murillo.mutalk.data.remote.PROPERTY_NAME
import com.maciel.murillo.mutalk.domain.model.User

data class UserData(
    var id: String = "",
    var email: String = "",
    var imageUrl: String = "",

    @get:PropertyName(PROPERTY_NAME)
    @set:PropertyName(PROPERTY_NAME)
    var name: String = "",
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