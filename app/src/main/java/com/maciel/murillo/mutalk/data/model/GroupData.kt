package com.maciel.murillo.mutalk.data.model

import com.maciel.murillo.mutalk.domain.model.Group

data class GroupData(
    val id: String = "",
    val name: String = "",
    val image: String = "",
    val members: List<UserData> = emptyList(),
)

fun GroupData.mapToGroup() = Group(
    id = this.id,
    name = this.name,
    image = this.image,
    members = this.members.map { it.mapToUser() },
)

fun Group.mapToGroupData() = GroupData(
    id = this.id,
    name = this.name,
    image = this.image,
    members = this.members.map { it.mapToUserData() },
)