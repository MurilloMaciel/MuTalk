package com.example.image_picker.data.repository

import com.example.image_picker.data.datasource.RemoteDataSource
import com.example.image_picker.domain.model.ImagePath
import com.example.image_picker.domain.repository.Repository

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath) =
        remoteDataSource.saveImage(imageBytes, imagePath)
}