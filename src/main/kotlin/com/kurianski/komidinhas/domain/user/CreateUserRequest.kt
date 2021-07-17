package com.kurianski.komidinhas.domain.user

data class CreateUserRequest (
    val username: String,
    var password: String,
    var email: String
)
