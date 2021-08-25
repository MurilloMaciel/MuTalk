package com.maciel.murillo.user.data.mapper

import com.maciel.murillo.user.data.model.UserData
import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class UserModelToUserDataMapper @Inject constructor() : Mapper<User, UserData> {
    override fun mapFrom(from: User) = UserData(
        id = from.id,
        name = from.name,
        email = from.email,
        imageUrl = from.imageUrl,
    )
}