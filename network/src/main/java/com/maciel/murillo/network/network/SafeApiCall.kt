package com.maciel.murillo.network.network

import com.maciel.murillo.network.model.HttpStatus
import retrofit2.Response
import com.maciel.murillo.util.result.Result

suspend fun <T> Response<T>.safeCall(): Result<T, HttpStatus> {
    return if (isSuccessful && body() != null) {
        Result.Success(body()!!)
    } else {
        Result.Error(mapToHttpStatus(code()))
    }
}

private fun mapToHttpStatus(code: Int) = when (code) {
    400 -> HttpStatus.BAD_REQUEST
    401 -> HttpStatus.UNAUTHORIZED
    403 -> HttpStatus.FORBIDDEN
    404 -> HttpStatus.NOT_FOUND
    417 -> HttpStatus.EXPECTATION_FAILED
    422 -> HttpStatus.UNPROCESSABLE_ENTITY
    423 -> HttpStatus.LOCKED
    424 -> HttpStatus.FAILED_DEPENDENCY
    else -> HttpStatus.UNKNOWN
}