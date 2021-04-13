package com.example.image_picker.domain.repository

import com.example.image_picker.domain.model.ImagePath

interface Repository {

    suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath): String
}