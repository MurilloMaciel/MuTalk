package com.maciel.murillo.image_picker.data.repository

import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.repository.ImagePickerRepository
import javax.inject.Inject

class ImagePickerRepositoryImpl @Inject constructor(
    private val remoteDataSource: ImagePickerRemoteDataSource
) : ImagePickerRepository {

    override suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath) =
        remoteDataSource.saveImage(imageBytes, imagePath)
}