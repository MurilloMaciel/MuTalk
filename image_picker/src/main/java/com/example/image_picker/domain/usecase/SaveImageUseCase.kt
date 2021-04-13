package com.example.image_picker.domain.usecase

import com.example.image_picker.domain.model.ImagePath
import com.example.image_picker.domain.repository.Repository

class SaveImageUseCase(private val repository: Repository) {

    suspend operator fun invoke(imageBytes: ByteArray, imagePath: ImagePath) =
        repository.saveImage(imageBytes, imagePath)
}