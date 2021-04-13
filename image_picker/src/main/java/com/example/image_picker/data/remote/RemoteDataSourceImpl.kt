package com.example.image_picker.data.remote

import com.example.image_picker.data.datasource.RemoteDataSource
import com.example.image_picker.domain.model.ImageDestiny
import com.example.image_picker.domain.model.ImagePath
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await

private const val STORAGE_NODE_IMAGES = "images"
private const val GROUP_NODE_IMAGES = "group"
private const val CHAT_NODE_IMAGES = "chat"
private const val PROFILE_NODE_IMAGES = "profile"

class RemoteDataSourceImpl(
    private val storage: StorageReference,
) : RemoteDataSource {

    override suspend fun saveImage(imageBytes: ByteArray, imagePath: ImagePath): String {
        val uploadTask = storage.child(STORAGE_NODE_IMAGES)
            .child(getDestinyNodePath(imagePath.imageDestiny))
            .child(imagePath.elementId)
            .putBytes(imageBytes)
            .await()
        return uploadTask
            .storage
            .downloadUrl
            .await()
            .toString()
    }

    private fun getDestinyNodePath(imageDestiny: ImageDestiny) = when (imageDestiny) {
        ImageDestiny.PROFILE -> PROFILE_NODE_IMAGES
        ImageDestiny.GROUP -> GROUP_NODE_IMAGES
        ImageDestiny.CHAT -> CHAT_NODE_IMAGES
    }
}