package com.maciel.murillo.gif_finder.data.local.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import com.maciel.murillo.gif_finder.GifListProto

private const val CACHE_FILE_NAME = "mutalk_gif_finder.pb"

internal fun Context.createCacheDatastore(
    serializer: Serializer<GifListProto>,
): DataStore<GifListProto> {
    return DataStoreFactory.create(
        serializer = serializer,
        produceFile = { dataStoreFile(CACHE_FILE_NAME) },
    )
}