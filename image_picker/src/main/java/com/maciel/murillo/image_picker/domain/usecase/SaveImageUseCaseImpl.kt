package com.maciel.murillo.image_picker.domain.usecase

import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.maciel.murillo.image_picker.domain.repository.ImagePickerRepository
import com.maciel.murillo.image_picker.domain.usecase.SaveImageUseCase
import javax.inject.Inject

class SaveImageUseCaseImpl @Inject constructor(
    private val repository: ImagePickerRepository
) : SaveImageUseCase {

    override suspend operator fun invoke(imageBytes: ByteArray, imagePath: ImagePath) =
        repository.saveImage(imageBytes, imagePath)
}