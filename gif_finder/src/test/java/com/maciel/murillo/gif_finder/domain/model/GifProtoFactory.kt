package com.maciel.murillo.gif_finder.domain.model

import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.GifProto
import com.maciel.murillo.gif_finder.ImageContentProto
import com.maciel.murillo.gif_finder.ImagesProto
import java.util.*

object GifProtoFactory {
    fun makeGifListProto(
        gifs: List<GifProto> = listOf(makeGifProto()),
        timestamp: Long = Date().time
    ) = GifListProto(gifs, timestamp)

    fun makeGifProto(
        type: String = "type",
        id: String = "id",
        url: String = "url",
        rating: String = "rating",
        title: String = "title",
    ) = GifProto(
        type = type,
        id = id,
        url = url,
        rating = rating,
        title = title,
        images = ImagesProto(
            fixedWidthSmall = makeImageContentProto(),
            fixedHeightSmall = makeImageContentProto(),
            fixedWidth = makeImageContentProto(),
            fixedHeight = makeImageContentProto(),
        ),
    )

    private fun makeImageContentProto() = ImageContentProto(
        url = "url",
        width = "width",
        height = "height",
        mp4 = "mp4",
        webp = "webp",
    )
}