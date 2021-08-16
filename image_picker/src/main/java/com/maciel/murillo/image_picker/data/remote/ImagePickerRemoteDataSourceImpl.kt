package com.maciel.murillo.image_picker.data.remote

import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImageDestiny
import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val STORAGE_NODE_IMAGES = "images"
private const val GROUP_NODE_IMAGES = "group"
private const val CHAT_NODE_IMAGES = "chat"
private const val PROFILE_NODE_IMAGES = "profile"

class RemoteDataSourceImpl @Inject constructor(
    private val storage: StorageReference,
) : ImagePickerRemoteDataSource {

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