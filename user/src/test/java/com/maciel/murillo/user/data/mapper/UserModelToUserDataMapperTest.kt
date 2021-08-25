package com.maciel.murillo.user.data.mapper

import com.maciel.murillo.user.data.model.UserDataFactory
import com.maciel.murillo.user.domain.model.UserFactory
import org.junit.Test
import kotlin.test.assertEquals

class UserModelToUserDataMapperTest {

    private val mapper = UserModelToUserDataMapper()

    @Test
    fun mapFrom_returnUser() {
        val user = UserFactory.makeUser()

        val result = mapper.mapFrom(user)

        val expect = UserDataFactory.makeUserData()

        assertEquals(expect, result)
    }
}