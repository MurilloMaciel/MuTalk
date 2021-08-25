package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersByNameUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetUsersByNameUseCase {

    override suspend operator fun invoke(filterName: String?) = if (filterName.isNullOrBlank()) {
        repository.getAll()
    } else {
        repository.getByName(filterName)
    }
}