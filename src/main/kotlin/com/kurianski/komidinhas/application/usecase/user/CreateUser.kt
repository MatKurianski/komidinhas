package com.kurianski.komidinhas.application.usecase.user

import com.kurianski.komidinhas.application.datastore.UserRepository
import com.kurianski.komidinhas.domain.User
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CreateUser(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    fun execute(createUserRequest: CreateUserRequest): Result<User> {
        createUserRequest.password = bCryptPasswordEncoder.encode(createUserRequest.password)
        return userRepository.createUser(createUserRequest)
    }
}