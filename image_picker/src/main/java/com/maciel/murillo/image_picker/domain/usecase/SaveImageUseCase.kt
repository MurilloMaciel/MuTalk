package com.maciel.murillo.image_picker.domain.usecase

import com.maciel.murillo.image_picker.domain.model.ImagePath

interface SaveImageUseCase {
    suspend operator fun invoke(imageBytes: ByteArray, imagePath: ImagePath): String
}