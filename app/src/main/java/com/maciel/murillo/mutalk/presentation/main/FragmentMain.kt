package com.maciel.murillo.mutalk.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.base.BaseFragment
import com.maciel.murillo.mutalk.databinding.FragmentMainBinding

class FragmentMain : BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding =
        FragmentMainBinding::inflate

    override fun setUpViews() = with(binding) {
        viewPager.adapter = TabsAdapter(childFragmentManager, requireContext())
        tabs.setupWithViewPager(viewPager)
    }
}