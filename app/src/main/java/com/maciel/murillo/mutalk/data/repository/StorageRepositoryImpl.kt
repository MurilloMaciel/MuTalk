package com.maciel.murillo.mutalk.data.repository

import com.maciel.murillo.mutalk.data.datasource.StorageDataSource
import com.maciel.murillo.mutalk.domain.repository.StorageRepository

class StorageRepositoryImpl(
    private val storageDataSource: StorageDataSource
) : StorageRepository {

    override suspend fun saveImage(imagesBytes: ByteArray) =
        storageDataSource.saveImage(imagesBytes)
}