package com.maciel.murillo.mutalk.presentation.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentSignupBinding

class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignupBinding =
        FragmentSignupBinding::inflate
}