package com.maciel.murillo.mutalk.data.model

import com.google.firebase.firestore.PropertyName
import com.maciel.murillo.mutalk.data.remote.PROPERTY_MEMBERS

data class GroupData(
    override var id: String = "",
    override var messages: List<MessageData> = emptyList(),
    var name: String = "",
    var imageUrl: String = "",

    @get:PropertyName(PROPERTY_MEMBERS)
    @set:PropertyName(PROPERTY_MEMBERS)
    override var members: List<UserData> = emptyList(),
) : ChatData()