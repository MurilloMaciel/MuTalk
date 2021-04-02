package com.maciel.murillo.mutalk.domain.usecase.storage

import com.maciel.murillo.mutalk.domain.repository.StorageRepository

class SaveImageUseCase(
    private val repository: StorageRepository
) {

    suspend operator fun invoke(imagesBytes: ByteArray) =
        repository.saveImage(imagesBytes)
}