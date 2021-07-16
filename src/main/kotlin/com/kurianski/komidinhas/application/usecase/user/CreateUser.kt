package com.kurianski.komidinhas.application.usecase.user

import com.kurianski.komidinhas.application.datastore.UserRepository
import com.kurianski.komidinhas.domain.User
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import org.springframework.stereotype.Component

@Component
class CreateUser(private val userRepository: UserRepository) {
    fun execute(createUserRequest: CreateUserRequest): Result<User> =
        userRepository.createUser(createUserRequest)
}