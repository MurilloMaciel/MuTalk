package com.maciel.murillo.mutalk.presentation.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentChatBinding

class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentChatBinding =
        FragmentChatBinding::inflate
}