package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.data.model.GifResponse
import com.maciel.murillo.gif_finder.data.model.ImageContentData
import com.maciel.murillo.gif_finder.data.model.ImagesData
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.model.ImageContent
import com.maciel.murillo.gif_finder.domain.model.Images
import com.maciel.murillo.util.mapper.Mapper
import javax.inject.Inject

class GifResponseToModelMapper @Inject constructor() : Mapper<GifResponse?, List<Gif>> {

    override fun mapFrom(from: GifResponse?): List<Gif> {
        return from?.gifs?.map { gifData ->
            Gif(
                type = gifData.type,
                id = gifData.id,
                url = gifData.url,
                rating = gifData.rating,
                title = gifData.title,
                images = getImages(gifData.images),
            )
        } ?: emptyList()
    }

    private fun getImages(imagesData: ImagesData) = Images(
        fixedHeight = getImageContent(imagesData.fixedHeight),
        fixedHeightSmall = getImageContent(imagesData.fixedHeightSmall),
        fixedWidth = getImageContent(imagesData.fixedWidth),
        fixedWidthSmall = getImageContent(imagesData.fixedWidthSmall),
    )

    private fun getImageContent(contentData: ImageContentData) = ImageContent(
        url = contentData.url,
        width = contentData.width,
        height = contentData.height,
        mp4 = contentData.mp4,
        webp = contentData.webp,
    )
}