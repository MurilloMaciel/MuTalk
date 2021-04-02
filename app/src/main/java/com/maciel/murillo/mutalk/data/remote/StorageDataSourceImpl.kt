package com.maciel.murillo.mutalk.data.remote

import com.google.firebase.storage.StorageReference
import com.maciel.murillo.mutalk.data.datasource.StorageDataSource
import kotlinx.coroutines.tasks.await

const val STORAGE_NODE_IMAGES = "images"
const val STORAGE_NODE_ADS = "ads"
const val STORAGE_PREFIX_IMAGE = "image"

class StorageDataSourceImpl(
    private val storage: StorageReference,
) : StorageDataSource {

    override suspend fun saveImage(imagesBytes: ByteArray): String {
//        val uploadTask = storage.child(STORAGE_NODE_IMAGES)
//            .child(STORAGE_NODE_ADS)
//            .child(adId)
//            .child(STORAGE_PREFIX_IMAGE + position)
//            .putBytes(imageBytes)
//            .await()
//        return uploadTask
//            .storage
//            .downloadUrl
//            .await()
//            .toString()
        return ""
    }
}