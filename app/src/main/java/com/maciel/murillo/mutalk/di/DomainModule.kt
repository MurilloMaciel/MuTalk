package com.maciel.murillo.mutalk.di

import com.maciel.murillo.mutalk.domain.usecase.auth.IsUserLoggedUseCase
import com.maciel.murillo.mutalk.domain.usecase.auth.LoginUseCase
import com.maciel.murillo.mutalk.domain.usecase.auth.LogoutUseCase
import com.maciel.murillo.mutalk.domain.usecase.auth.SignupUseCase
import com.maciel.murillo.mutalk.domain.usecase.user.*
import org.koin.dsl.module

val domainModule = module {

    factory { IsUserLoggedUseCase(repository = get()) }
    factory { LoginUseCase(repository = get()) }
    factory { SignupUseCase(repository = get()) }
    factory { LogoutUseCase(repository = get()) }

    factory { DeleteUserUseCase(repository = get()) }
    factory { InsertUserUseCase(repository = get()) }
    factory { GetUserUseCase(repository = get()) }
    factory { GetUsersByNameUseCase(repository = get()) }
    factory { UpdateUserUseCase(repository = get()) }
}