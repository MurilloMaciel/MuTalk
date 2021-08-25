package com.maciel.murillo.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.maciel.murillo.auth.data.datasource.AuthRemoteDataSource
import com.maciel.murillo.auth.data.remote.AuthRemoteDataSourceImpl
import com.maciel.murillo.auth.data.repository.AuthRepositoryImpl
import com.maciel.murillo.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AuthDataModule {

    @Binds
    fun bindRepository(repository: AuthRepositoryImpl): AuthRepository

    @Binds
    fun bindRemoteDataSource(dataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    companion object {
        @Provides
        fun provideDatastore(): FirebaseAuth = FirebaseAuth.getInstance()
    }
}