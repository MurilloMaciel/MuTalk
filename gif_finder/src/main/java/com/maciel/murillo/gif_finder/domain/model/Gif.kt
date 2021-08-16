package com.maciel.murillo.gif_finder.domain.model

data class Gif(
    val type: String,
    val id: String,
    val url: String,
    val rating: String,
    val title: String,
    val images: Images,
)

data class Images(
    val fixedHeight: ImageContent,
    val fixedWidth: ImageContent,
    val fixedHeightSmall: ImageContent,
    val fixedWidthSmall: ImageContent,
)

data class ImageContent(
    val url: String,
    val width: String,
    val height: String,
    val mp4: String,
    val webp: String,
)