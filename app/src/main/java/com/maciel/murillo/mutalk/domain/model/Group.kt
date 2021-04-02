package com.maciel.murillo.mutalk.domain.model

data class Group(
    val id: String,
    val name: String,
    val image: String,
    val members: List<User>,
)