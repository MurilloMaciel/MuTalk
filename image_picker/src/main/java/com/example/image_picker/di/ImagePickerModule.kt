package com.example.image_picker.di

import android.content.ComponentCallbacks
import com.example.core.helper.closeScope
import com.example.core.helper.getOrCreateScope
import com.example.image_picker.data.datasource.RemoteDataSource
import com.example.image_picker.data.remote.RemoteDataSourceImpl
import com.example.image_picker.data.repository.RepositoryImpl
import com.example.image_picker.domain.repository.Repository
import com.example.image_picker.domain.usecase.SaveImageUseCase
import com.example.image_picker.presentation.ImagePickerViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val IMAGE_PICKER_SCOPE = "IMAGE_PICKER_SCOPE"
const val IMAGE_PICKER_SCOPE_ID = "IMAGE_PICKER_SCOPE_ID"

fun ComponentCallbacks.getOrCreateImagePickerScope() =
    getOrCreateScope(this, IMAGE_PICKER_SCOPE_ID, IMAGE_PICKER_SCOPE) {
        imagePickerModule
    }

fun ComponentCallbacks.closeImagePickerScope() = closeScope(this, IMAGE_PICKER_SCOPE_ID)

val imagePickerModule: Module = module {

    scope(named(IMAGE_PICKER_SCOPE)) {

        viewModel {
            ImagePickerViewModel(
                saveImageUseCase = get(),
            )
        }

        factory { SaveImageUseCase(repository = get()) }

        scoped<StorageReference> { FirebaseStorage.getInstance().reference }
        scoped<RemoteDataSource> { RemoteDataSourceImpl(storage = get()) }
        scoped<Repository> { RepositoryImpl(remoteDataSource = get()) }
    }
}