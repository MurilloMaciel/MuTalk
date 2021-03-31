package com.maciel.murillo.mutalk.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding =
        FragmentLoginBinding::inflate
}