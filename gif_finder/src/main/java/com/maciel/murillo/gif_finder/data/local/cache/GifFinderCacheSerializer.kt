package com.maciel.murillo.gif_finder.data.local.cache

import androidx.datastore.core.Serializer
import com.maciel.murillo.gif_finder.GifListProto
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

internal object GifFinderCacheSerializer : Serializer<GifListProto> {

    override val defaultValue: GifListProto
        get() = GifListProto(gifs = emptyList())

    override suspend fun readFrom(input: InputStream): GifListProto {
        return if (input.available() != 0) try {
            GifListProto.ADAPTER.decode(input)
        } catch (e: IOException) {
            throw GifFinderCacheSerializerException.Read(e)
        } else {
            defaultValue
        }
    }

    override suspend fun writeTo(t: GifListProto, output: OutputStream) {
        try {
            GifListProto.ADAPTER.encode(output, t)
        } catch (e: IOException) {
            throw GifFinderCacheSerializerException.Write(e)
        }
    }
}
