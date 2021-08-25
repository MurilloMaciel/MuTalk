package com.maciel.murillo.user.data.mapper

import com.maciel.murillo.user.data.model.UserDataFactory
import com.maciel.murillo.user.domain.model.UserFactory
import org.junit.Test
import kotlin.test.assertEquals

class UserDataToModelMapperTest {

    private val mapper = UserDataToModelMapper()

    @Test
    fun mapFrom_returnUser() {
        val userData = UserDataFactory.makeUserData()

        val result = mapper.mapFrom(userData)

        val expect = UserFactory.makeUser()

        assertEquals(expect, result)
    }
}