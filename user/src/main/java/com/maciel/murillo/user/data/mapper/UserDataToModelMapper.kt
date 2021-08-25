package com.maciel.murillo.user.data.mapper

import com.maciel.murillo.user.data.model.UserData
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class UserDataToModelMapper @Inject constructor() : Mapper<UserData, User> {
    override fun mapFrom(from: UserData) = User(
        id = from.id,
        name = from.name,
        email = from.email,
        imageUrl = from.imageUrl,
    )
}