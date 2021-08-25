package com.maciel.murillo.auth.domain.model

data class UserInfo(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val imageUrl: String,
)
