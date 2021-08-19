package com.maciel.murillo.image_picker.data.datasource

import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.util.result.Result

interface ImagePickerRemoteDataSource {

    suspend fun saveImage(
        imageBytes: ByteArray,
        imagePath: ImagePath
    ): Result<String, ImagePickerError>
}