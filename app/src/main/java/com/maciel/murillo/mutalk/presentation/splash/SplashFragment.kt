package com.maciel.murillo.mutalk.presentation.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core.base.BaseFragment
import com.example.core.helper.EventObserver
import com.maciel.murillo.mutalk.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding =
        FragmentSplashBinding::inflate

    private val splashViewModel: SplashViewModel by viewModel()

    override fun setUpViews() {
        splashViewModel.onInit()
    }

    override fun setUpObservers() = with(splashViewModel) {
        navigateToLogin.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(SplashFragmentDirections.goToLogin())
        })

        navigateToMain.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(SplashFragmentDirections.goToMain())
        })
    }
}