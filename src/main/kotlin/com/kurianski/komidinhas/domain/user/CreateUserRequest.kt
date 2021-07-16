package com.kurianski.komidinhas.domain.user

data class CreateUserRequest (
    val username: String,
    val password: String,
    var email: String
)
