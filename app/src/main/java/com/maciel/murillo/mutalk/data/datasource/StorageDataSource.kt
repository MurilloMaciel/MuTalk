package com.maciel.murillo.mutalk.data.datasource

interface StorageDataSource {

    suspend fun saveImage(imagesBytes: ByteArray): String
}