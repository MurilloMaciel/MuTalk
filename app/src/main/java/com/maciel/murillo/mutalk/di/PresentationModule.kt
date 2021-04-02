package com.maciel.murillo.mutalk.di

import com.maciel.murillo.mutalk.presentation.login.LoginViewModel
import com.maciel.murillo.mutalk.presentation.settings.SettingsViewModel
import com.maciel.murillo.mutalk.presentation.signup.SignupViewModel
import com.maciel.murillo.mutalk.presentation.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        SplashViewModel(
            isUserLoggedUseCase = get(),
        )
    }

    viewModel {
        SignupViewModel(
            signupUseCase = get(),
        )
    }

    viewModel {
        LoginViewModel(
            loginUseCase = get(),
        )
    }

    viewModel {
        SettingsViewModel(
//            loginUseCase = get(),
        )
    }
}