package com.maciel.murillo.image_picker.data.datasource

import com.maciel.murillo.image_picker.domain.model.ImagePath

interface ImagePickerRemoteDataSource {

    suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath): String
}