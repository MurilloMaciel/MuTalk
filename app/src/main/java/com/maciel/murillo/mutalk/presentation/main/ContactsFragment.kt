package com.maciel.murillo.mutalk.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentContactsBinding

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactsBinding =
        FragmentContactsBinding::inflate

    companion object {

        @JvmStatic
        fun newInstance(): ContactsFragment {
            return ContactsFragment()
        }
    }
}