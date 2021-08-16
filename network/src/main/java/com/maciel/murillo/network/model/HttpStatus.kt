package com.maciel.murillo.network.model

enum class HttpStatus(val code: Int) {
    UNKNOWN(0),
    OK(200),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    EXPECTATION_FAILED(417),
    LOCKED(423),
    FAILED_DEPENDENCY(424),
    UNPROCESSABLE_ENTITY(422),
}