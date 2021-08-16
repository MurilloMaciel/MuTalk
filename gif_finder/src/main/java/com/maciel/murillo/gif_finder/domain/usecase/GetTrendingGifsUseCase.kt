package com.maciel.murillo.gif_finder.domain.usecase

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.util.result.Result

interface GetTrendingGifsUseCase {

    suspend operator fun invoke(): Result<List<Gif>, GetGifsError>
}