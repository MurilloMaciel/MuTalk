package com.maciel.murillo.mutalk.presentation.addgroup

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentAddGroupBinding

class AddGroupFragment : BaseFragment<FragmentAddGroupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddGroupBinding =
        FragmentAddGroupBinding::inflate
}