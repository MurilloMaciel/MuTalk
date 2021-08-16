package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.GifProto
import com.maciel.murillo.gif_finder.ImageContentProto
import com.maciel.murillo.gif_finder.ImagesProto
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.model.ImageContent
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class GifsToGifListProtoMapper @Inject constructor() : Mapper<List<Gif>, GifListProto> {

    override fun mapFrom(from: List<Gif>): GifListProto {
        return GifListProto(
            gifs = from.map { gif ->
                GifProto(
                    type = gif.type,
                    id = gif.id,
                    url = gif.url,
                    rating = gif.rating,
                    title = gif.title,
                    images = ImagesProto(
                        fixedHeight = getImageContentProto(gif.images.fixedHeight),
                        fixedHeightSmall = getImageContentProto(gif.images.fixedHeightSmall),
                        fixedWidth = getImageContentProto(gif.images.fixedWidth),
                        fixedWidthSmall = getImageContentProto(gif.images.fixedWidthSmall),
                    ),
                )
            }
        )
    }

    private fun getImageContentProto(content: ImageContent): ImageContentProto {
        return ImageContentProto(
            url = content.url,
            width = content.width,
            height = content.height,
            mp4 = content.mp4,
            webp = content.webp,
        )
    }
}