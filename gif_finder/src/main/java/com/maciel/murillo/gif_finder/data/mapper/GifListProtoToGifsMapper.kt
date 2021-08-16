package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.GifListProto
import com.maciel.murillo.gif_finder.ImageContentProto
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.model.ImageContent
import com.maciel.murillo.gif_finder.domain.model.Images
import com.maciel.murillo.util.extensions.safe
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class GifListProtoToGifsMapper @Inject constructor() : Mapper<GifListProto, List<Gif>> {

    override fun mapFrom(from: GifListProto): List<Gif> {
        return from.gifs.map { gifProto ->
            Gif(
                type = gifProto.type,
                id = gifProto.id,
                url = gifProto.url,
                rating = gifProto.rating,
                title = gifProto.title,
                images = Images(
                    fixedHeight = getImageContent(gifProto.images?.fixedHeight),
                    fixedHeightSmall = getImageContent(gifProto.images?.fixedHeightSmall),
                    fixedWidth = getImageContent(gifProto.images?.fixedWidth),
                    fixedWidthSmall = getImageContent(gifProto.images?.fixedWidthSmall),
                ),
            )
        }
    }

    private fun getImageContent(content: ImageContentProto?): ImageContent {
        return ImageContent(
            url = content?.url.safe(),
            width = content?.width.safe(),
            height = content?.height.safe(),
            mp4 = content?.mp4.safe(),
            webp = content?.webp.safe(),
        )
    }
}