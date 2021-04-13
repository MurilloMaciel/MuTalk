package com.maciel.murillo.mutalk.data.model

import com.google.firebase.firestore.PropertyName
import com.maciel.murillo.mutalk.data.remote.PROPERTY_MEMBERS
import com.maciel.murillo.mutalk.data.remote.PROPERTY_MESSAGES

data class ContactChatData(
    override var id: String,

    @get:PropertyName(PROPERTY_MESSAGES)
    @set:PropertyName(PROPERTY_MESSAGES)
    override var messages: List<MessageData> = emptyList(),

    @get:PropertyName(PROPERTY_MEMBERS)
    @set:PropertyName(PROPERTY_MEMBERS)
    override var members: List<UserData> = emptyList(),
) : ChatData()