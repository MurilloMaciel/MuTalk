package com.maciel.murillo.image_picker.data.remote

import com.maciel.murillo.image_picker.data.datasource.ImagePickerRemoteDataSource
import com.maciel.murillo.image_picker.domain.model.ImageDestiny
import com.maciel.murillo.image_picker.domain.model.ImagePath
import com.google.firebase.storage.StorageReference
import com.maciel.murillo.image_picker.domain.model.ImagePickerError
import com.maciel.murillo.util.result.Result
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val STORAGE_NODE_IMAGES = "images"
private const val GROUP_NODE_IMAGES = "group"
private const val CHAT_NODE_IMAGES = "chat"
private const val PROFILE_NODE_IMAGES = "profile"

class ImagePickerRemoteDataSourceImpl @Inject constructor(
    private val storage: StorageReference,
) : ImagePickerRemoteDataSource {

    override suspend fun saveImage(
        imageBytes: ByteArray,
        imagePath: ImagePath
    ): Result<String, ImagePickerError> {
        return try {
            storage.child(STORAGE_NODE_IMAGES)
                .child(getDestinyNodePath(imagePath.imageDestiny))
                .child(imagePath.elementId)
                .putBytes(imageBytes)
                .await()
                .let { uploadTask ->
                    uploadTask.storage
                    .downloadUrl
                    .await()
                    .toString()
                    .let { handleImagePath(it) }
                }
        } catch (e: Exception) {
            Result.Error(ImagePickerError.SaveImageIntoDb)
        }
    }

    private fun handleImagePath(path: String) = if (path.isEmpty()) {
        Result.Success(path)
    } else {
        Result.Error(ImagePickerError.SaveImageIntoDb)
    }

    private fun getDestinyNodePath(imageDestiny: ImageDestiny) = when (imageDestiny) {
        ImageDestiny.PROFILE -> PROFILE_NODE_IMAGES
        ImageDestiny.GROUP -> GROUP_NODE_IMAGES
        ImageDestiny.CHAT -> CHAT_NODE_IMAGES
    }
}