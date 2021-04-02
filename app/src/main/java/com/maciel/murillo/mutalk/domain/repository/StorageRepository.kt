package com.maciel.murillo.mutalk.domain.repository

interface StorageRepository {

    suspend fun saveImage(imagesBytes: ByteArray): String
}