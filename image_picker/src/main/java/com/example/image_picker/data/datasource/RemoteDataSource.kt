package com.example.image_picker.data.datasource

import com.example.image_picker.domain.model.ImagePath

interface RemoteDataSource {

    suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath): String
}