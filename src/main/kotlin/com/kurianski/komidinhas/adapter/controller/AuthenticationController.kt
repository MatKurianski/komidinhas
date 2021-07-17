package com.kurianski.komidinhas.adapter.controller

import com.kurianski.komidinhas.adapter.mapper.toErrorResponseEntity
import com.kurianski.komidinhas.application.usecase.user.CreateUser
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class AuthenticationController(
    val createUser: CreateUser
) {

    @PostMapping("/signup")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<Any> =
        createUser.execute(createUserRequest).fold(
            onSuccess = { ResponseEntity.status(HttpStatus.CREATED).body(it) },
            onFailure = { it.toErrorResponseEntity() }
        )
}