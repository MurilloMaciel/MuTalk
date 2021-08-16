package com.maciel.murillo.gif_finder.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GifResponse(
    @field:Json(name = "data") val gifs: List<GifData>,
    val pagination: Pagination,
    val meta: Meta
)

@JsonClass(generateAdapter = true)
data class GifData(
    val type: String,
    val id: String,
    val url: String,
    val rating: String,
    val title: String,
    val images: ImagesData,
)

@JsonClass(generateAdapter = true)
data class Pagination(
    val offset: Int,
    val count: Int,
    @field:Json(name = "total_count") val totalCount: Int,
)

@JsonClass(generateAdapter = true)
data class Meta(
    val msg: String,
    val status: Int,
    @field:Json(name = "response_id") val responseId: String,
)

@JsonClass(generateAdapter = true)
data class ImagesData(
    @field:Json(name = "fixed_height") val fixedHeight: ImageContentData,
    @field:Json(name = "fixed_width") val fixedWidth: ImageContentData,
    @field:Json(name = "fixed_height_small") val fixedHeightSmall: ImageContentData,
    @field:Json(name = "fixed_width_small") val fixedWidthSmall: ImageContentData,
)

@JsonClass(generateAdapter = true)
data class ImageContentData(
    val url: String,
    val width: String,
    val height: String,
    val mp4: String,
    val webp: String,
)