package com.maciel.murillo.util.mapper

interface Mapper<in FROM, TO> {
    fun mapFrom(from: FROM): TO
}