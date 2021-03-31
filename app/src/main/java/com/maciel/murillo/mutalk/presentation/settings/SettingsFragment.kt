package com.maciel.murillo.mutalk.presentation.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maciel.murillo.mutalk.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSettingsBinding =
        FragmentSettingsBinding::inflate
}