package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.model.GifProtoFactory
import org.junit.Test
import kotlin.test.assertEquals

class GifListProtoToGifsMapperTest {

    private val mapper = GifListProtoToGifsMapper()

    @Test
    fun mapFrom_returnGifs() {
        val gifListProto = GifProtoFactory.makeGifListProto()

        val result = mapper.mapFrom(gifListProto)

        val gifs = listOf(GifFactory.makeGif())

        assertEquals(gifs, result)
    }
}