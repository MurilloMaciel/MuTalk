package com.maciel.murillo.gif_finder.data.local.cache

import java.io.IOException

internal sealed class GifFinderCacheSerializerException(throwable: Throwable) : IOException(throwable) {
    internal class Read(
        throwable: Throwable
    ) : GifFinderCacheSerializerException(throwable)

    internal class Write(
        throwable: Throwable
    ) : GifFinderCacheSerializerException(throwable)
}
