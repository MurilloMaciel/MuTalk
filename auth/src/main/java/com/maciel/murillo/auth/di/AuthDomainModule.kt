package com.maciel.murillo.auth.di

import com.maciel.murillo.auth.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AuthDomainModule {

    @Binds
    fun bindIsUserLoggedUseCase(useCase: IsUserLoggedUseCaseImpl): IsUserLoggedUseCase

    @Binds
    fun bindLoginUseCase(useCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindLogoutUseCase(useCase: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    fun bindSignupUseCase(useCase: SignupUseCaseImpl): SignupUseCase
}