package com.maciel.murillo.image_picker.di

import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.data.remote.RemoteDataSourceImpl
import com.maciel.murillo.image_picker.data.repository.ImagePickerRepositoryImpl
import com.maciel.murillo.image_picker.domain.repository.ImagePickerRepository
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ImagePickerDataModule {

    @Binds
    fun bindRepository(repository: ImagePickerRepositoryImpl): ImagePickerRepository

    @Binds
    fun bindRemoteDataSource(dataSource: RemoteDataSourceImpl): ImagePickerRemoteDataSource

    companion object {
        @Provides
        fun provideStorageRef(): StorageReference = FirebaseStorage.getInstance().reference
    }
}