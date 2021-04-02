package com.maciel.murillo.mutalk.domain.usecase.user

import com.maciel.murillo.mutalk.domain.repository.UserRepository

class GetUsersByNameUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(filterName: String?) = if (filterName.isNullOrBlank()) {
        repository.getAllUsers()
    } else {
        repository.getUsersByName(filterName)
    }
}