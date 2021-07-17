package com.kurianski.komidinhas.adapter.mapper

import com.kurianski.komidinhas.adapter.controller.entity.AuthenticatedUser
import com.kurianski.komidinhas.adapter.datastore.entity.UserEntity
import com.kurianski.komidinhas.domain.User
import com.kurianski.komidinhas.domain.UserWithCredentials
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import java.time.LocalDateTime

fun CreateUserRequest.toUserEntity(): UserEntity =
    UserEntity(
        username = username,
        password = password,
        email = email,
        created_on = LocalDateTime.now()
    )

fun UserEntity.toUser(): User = User(username)

fun UserEntity.toUserWithCredentials(): UserWithCredentials = UserWithCredentials(username, password)

fun UserWithCredentials.toAuthenticatedUser() = AuthenticatedUser(username, password)