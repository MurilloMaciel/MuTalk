package com.maciel.murillo.gif_finder.data.mapper

import com.maciel.murillo.gif_finder.domain.model.GifFactory
import com.maciel.murillo.gif_finder.domain.model.GifResponseFactory
import org.junit.Test
import kotlin.test.assertEquals

class GifResponseToModelMapperTest {

    private val mapper = GifResponseToModelMapper()

    @Test
    fun mapFrom_returnGifs() {
        val response = GifResponseFactory.makeGifResponse()

        val result = mapper.mapFrom(response)

        val gifs = listOf(GifFactory.makeGif())

        assertEquals(gifs, result)
    }
}