package com.maciel.murillo.gif_finder.domain.model

sealed class GetGifsError {
    object EmptyResult : GetGifsError()
    object Generic : GetGifsError()
}