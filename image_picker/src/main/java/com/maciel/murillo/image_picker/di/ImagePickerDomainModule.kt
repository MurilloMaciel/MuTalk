package com.maciel.murillo.image_picker.di

import com.maciel.murillo.image_picker.domain.usecase.SaveImageUseCase
import com.maciel.murillo.image_picker.domain.usecase.SaveImageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImagePickerDomainModule {

    @Binds
    fun bindSaveImageUseCase(useCase: SaveImageUseCaseImpl): SaveImageUseCase
}