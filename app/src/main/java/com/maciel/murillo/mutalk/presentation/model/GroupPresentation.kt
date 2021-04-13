//package com.maciel.murillo.mutalk.presentation.model
//
//import com.maciel.murillo.mutalk.domain.model.Group
//
//data class GroupPresentation(
//    val id: String = "",
//    val name: String = "",
//    val image: String = "",
//    val members: List<UserPresentation> = emptyList(),
//)
//
//fun GroupPresentation.mapToGroup() = Group(
//    id = this.id,
//    name = this.name,
//    imageUrl = this.image,
//    members = this.members.map { it.mapToUser() },
//)
//
//fun Group.mapToGroupPresentation() = GroupPresentation(
//    id = this.id,
//    name = this.name,
//    image = this.imageUrl,
//    members = this.members.map { it.mapToUserPresentation() },
//)