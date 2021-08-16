package com.maciel.murillo.image_picker.domain.repository

import com.maciel.murillo.image_picker.domain.model.ImagePath

interface ImagePickerRepository {

    suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath): String
}