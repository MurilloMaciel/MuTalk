package com.maciel.murillo.mutalk.presentation.groupregister

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentGroupRegisterBinding

class GroupRegisterFragment : BaseFragment<FragmentGroupRegisterBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupRegisterBinding =
        FragmentGroupRegisterBinding::inflate
}