package com.maciel.murillo.user.domain.usecase

import com.maciel.murillo.user.domain.model.User
import com.maciel.murillo.util.error.CrudError
import com.maciel.murillo.util.result.Result

interface DeleteUserUseCase {

    suspend operator fun invoke(user: User): Result<User, CrudError.Delete>
}