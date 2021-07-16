package com.kurianski.komidinhas.application.datastore

import com.kurianski.komidinhas.domain.User
import com.kurianski.komidinhas.domain.user.CreateUserRequest

interface UserRepository {
    fun createUser(createUserRequest: CreateUserRequest): Result<User>
}