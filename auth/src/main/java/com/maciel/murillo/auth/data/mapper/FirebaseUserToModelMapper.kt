package com.maciel.murillo.auth.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.maciel.murillo.auth.domain.model.UserInfo
import com.maciel.murillo.extensions.safe
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class FirebaseUserToModelMapper @Inject constructor() : Mapper<FirebaseUser, UserInfo> {

    override fun mapFrom(from: FirebaseUser): UserInfo {
        return UserInfo(
            id = from.uid.safe(),
            name = from.displayName.safe(),
            email = from.email.safe(),
            phone = from.phoneNumber.safe(),
            imageUrl = from.photoUrl?.toString().safe(),
        )
    }
}