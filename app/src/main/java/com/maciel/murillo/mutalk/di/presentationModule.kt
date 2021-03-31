package com.maciel.murillo.mutalk.di

import com.maciel.murillo.mutalk.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SplashViewModel(
            isUserLoggedUseCase = get(),
        )
    }
}