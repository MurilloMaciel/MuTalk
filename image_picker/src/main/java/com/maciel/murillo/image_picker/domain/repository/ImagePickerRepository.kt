package com.maciel.murillo.image_picker.domain.repository

import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.util.result.Result

interface ImagePickerRepository {

    suspend fun saveImage(
        imageBytes: ByteArray,
        imagePath: ImagePath
    ): Result<String, ImagePickerError>
}