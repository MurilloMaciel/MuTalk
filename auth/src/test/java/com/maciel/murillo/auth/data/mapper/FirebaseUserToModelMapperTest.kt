package com.maciel.murillo.auth.data.mapper

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import com.maciel.murillo.auth.domain.model.UserInfoFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class FirebaseUserToModelMapperTest {

    private val firebaseUser: FirebaseUser = mockk(relaxed = true)
    private val mapper: FirebaseUserToModelMapper = FirebaseUserToModelMapper()

    @Test
    fun mapFrom_returnUserInfo() {
        every { firebaseUser.photoUrl } returns Uri.parse("")
        every { firebaseUser.uid } returns "id"
        every { firebaseUser.displayName } returns "name"
        every { firebaseUser.email } returns "email"
        every { firebaseUser.phoneNumber } returns "phone"
        val expected = UserInfoFactory.makeUserInfo(imageUrl = "")

        val result = mapper.mapFrom(firebaseUser)

        assertEquals(expected, result)
    }
}