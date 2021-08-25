package com.maciel.murillo.user.di

import com.maciel.murillo.user.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UserDomainModule {

    @Binds
    fun bindDeleteUserUseCase(useCase: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    fun bindGetUsersByNameUseCase(useCase: GetUsersByNameUseCaseImpl): GetUsersByNameUseCase

    @Binds
    fun bindGetUserUseCase(useCase: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    fun bindInsertUserUseCase(useCase: InsertUserUseCaseImpl): InsertUserUseCase

    @Binds
    fun bindUpdateUserUseCase(useCase: UpdateUserUseCaseImpl): UpdateUserUseCase
}