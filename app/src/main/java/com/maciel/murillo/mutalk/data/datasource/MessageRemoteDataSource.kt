package com.maciel.murillo.mutalk.data.datasource

interface MessageRemoteDataSource {

    suspend fun sendGroupMessage()

    suspend fun sendContactMessage()
}