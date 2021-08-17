package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.model.GifProtoFactory
import org.junit.Test
import kotlin.test.assertEquals

class GifsToGifListProtoMapperTest {

    private val mapper = GifsToGifListProtoMapper()

    @Test
    fun mapFrom_returnGifs() {
        val gifs = listOf(GifFactory.makeGif())

        val result = mapper.mapFrom(gifs)

        val gifListProto = GifProtoFactory.makeGifListProto(timestamp = 0)

        assertEquals(gifListProto, result)
    }
}