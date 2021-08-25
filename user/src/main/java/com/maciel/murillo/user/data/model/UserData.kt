package com.maciel.murillo.user.data.model

import com.google.firebase.firestore.PropertyName

const val PROPERTY_NAME = "username"

data class UserData(
    var id: String = "",
    var email: String = "",
    var imageUrl: String = "",

    @get:PropertyName(PROPERTY_NAME)
    @set:PropertyName(PROPERTY_NAME)
    var name: String = "",
)