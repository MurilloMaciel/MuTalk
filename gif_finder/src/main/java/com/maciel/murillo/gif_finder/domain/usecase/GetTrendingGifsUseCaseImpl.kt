package com.maciel.murillo.gif_finder.domain.usecase

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import com.maciel.murillo.util.result.Result
import javax.inject.Inject

class GetTrendingGifsUseCaseImpl @Inject constructor(
    private val repository: GifFinderRepository
) : GetTrendingGifsUseCase {

    override suspend fun invoke(): Result<List<Gif>, GetGifsError> {
        return repository.getTrendingGifs()
    }
}