package com.maciel.murillo.user.di

import com.google.firebase.firestore.FirebaseFirestore
import com.maciel.murillo.user.data.datasource.UserRemoteDataSource
import com.maciel.murillo.user.data.remote.UserRemoteDataSourceImpl
import com.maciel.murillo.user.data.repository.UserRepositoryImpl
import com.maciel.murillo.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UserDataModule {

    @Binds
    fun bindRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    fun bindRemoteDataSource(dataSource: UserRemoteDataSourceImpl): UserRemoteDataSource

    companion object {
        @Provides
        fun provideDatastore(): FirebaseFirestore = FirebaseFirestore.getInstance()
    }
}