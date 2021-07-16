package com.kurianski.komidinhas.domain.exception

class UserAlreadyExists : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, exception: Throwable) : super(message, exception)
}
