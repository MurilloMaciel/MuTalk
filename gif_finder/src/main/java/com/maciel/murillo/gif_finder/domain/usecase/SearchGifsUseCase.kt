package com.maciel.murillo.gif_finder.domain.usecase

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.util.result.Result

interface SearchGifsUseCase {

    suspend operator fun invoke(query: String): Result<List<Gif>, GetGifsError>
}