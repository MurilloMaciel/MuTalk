package com.maciel.murillo.gif_finder.domain.usecase

import com.maciel.murillo.gif_finder.domain.model.GetGifsError
import com.maciel.murillo.gif_finder.domain.model.Gif
import com.maciel.murillo.gif_finder.domain.repository.GifFinderRepository
import com.maciel.murillo.util.result.Result
import com.maciel.murillo.gif_finder.domain.usecase.SearchGifsUseCase
import javax.inject.Inject

class SearchGifsUseCaseImpl @Inject constructor(
    private val repository: GifFinderRepository
) : SearchGifsUseCase {

    override suspend fun invoke(query: String): Result<List<Gif>, GetGifsError> {
        return repository.search(query)
    }
}