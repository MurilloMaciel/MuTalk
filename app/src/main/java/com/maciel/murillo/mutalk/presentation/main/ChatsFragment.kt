package com.maciel.murillo.mutalk.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentContactChatsBinding

class ChatsFragment : BaseFragment<FragmentContactChatsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactChatsBinding =
        FragmentContactChatsBinding::inflate

    companion object {

        @JvmStatic
        fun newInstance(): ChatsFragment {
            return ChatsFragment()
        }
    }
}