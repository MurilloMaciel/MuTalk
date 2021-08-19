package com.maciel.murillo.image_picker.domain.usecase

import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.util.result.Result

interface SaveImageUseCase {

    suspend operator fun invoke(
        imageBytes: ByteArray,
        imagePath: ImagePath
    ): Result<String, ImagePickerError>
}