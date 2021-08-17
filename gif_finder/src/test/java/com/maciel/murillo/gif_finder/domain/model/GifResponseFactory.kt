package com.maciel.murillo.gif_finder.domain.model

import com.maciel.murillo.gif_finder.data.model.*

object GifResponseFactory {
    fun makeGifResponse(
        gifs: List<GifData> = listOf(makeGifData()),
        meta: Meta = makeMeta(),
        pagination: Pagination = makePagination(),
    ) = GifResponse(gifs, pagination, meta)

    fun makePagination(
        count: Int = 0,
        offset: Int = 0,
        totalCount: Int = 0,
    ) = Pagination(count, offset, totalCount)

    fun makeMeta(
        msg: String = "msg",
        status: Int = 0,
        responseId: String = "responseId",
    ) = Meta(msg, status, responseId)

    fun makeGifData(
        type: String = "type",
        id: String = "id",
        url: String = "url",
        rating: String = "rating",
        title: String = "title",
    ) = GifData(
        type = type,
        id = id,
        url = url,
        rating = rating,
        title = title,
        images = ImagesData(
            fixedWidthSmall = makeImageContentData(),
            fixedHeightSmall = makeImageContentData(),
            fixedWidth = makeImageContentData(),
            fixedHeight = makeImageContentData(),
        ),
    )

    private fun makeImageContentData() = ImageContentData(
        url = "url",
        width = "width",
        height = "height",
        mp4 = "mp4",
        webp = "webp",
    )
}