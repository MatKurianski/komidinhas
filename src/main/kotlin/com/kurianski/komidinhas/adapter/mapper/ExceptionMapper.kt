package com.kurianski.komidinhas.adapter.mapper

import com.kurianski.komidinhas.adapter.controller.response.ErrorResponse
import com.kurianski.komidinhas.domain.exception.UserAlreadyExists
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

fun Throwable.toErrorResponseEntity(): ResponseEntity<Any> =
    when (this) {
        is UserAlreadyExists -> makeErrorResponse(HttpStatus.CONFLICT, this.message)
        else -> makeErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error")
    }

private fun makeErrorResponse(httpStatus: HttpStatus, message: String? = null): ResponseEntity<Any> =
    ResponseEntity.status(httpStatus).body(ErrorResponse(message ?: ""))
