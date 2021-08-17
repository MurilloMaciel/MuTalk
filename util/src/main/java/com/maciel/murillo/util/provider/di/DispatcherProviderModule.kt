package com.maciel.murillo.util.provider.di

import com.maciel.murillo.util.provider.DispatcherProvider
import com.maciel.murillo.util.provider.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DispatcherModule {
    @Binds
    fun bindDispatcherProvider(dispatcherProvider: DispatcherProviderImpl): DispatcherProvider
}