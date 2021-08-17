package com.maciel.murillo.gif_finder.domain.model

object GifFactory {
    fun makeGif(
        type: String = "type",
        id: String = "id",
        url: String = "url",
        rating: String = "rating",
        title: String = "title",
    ) = Gif(
        type = type,
        id = id,
        url = url,
        rating = rating,
        title = title,
        images = Images(
            fixedWidthSmall = makeImageContent(),
            fixedHeightSmall = makeImageContent(),
            fixedWidth = makeImageContent(),
            fixedHeight = makeImageContent(),
        ),
    )

    private fun makeImageContent() = ImageContent(
        url = "url",
        width = "width",
        height = "height",
        mp4 = "mp4",
        webp = "webp",
    )
}