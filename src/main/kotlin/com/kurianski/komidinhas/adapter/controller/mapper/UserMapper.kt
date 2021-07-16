package com.kurianski.komidinhas.adapter.controller.mapper

import com.kurianski.komidinhas.adapter.entity.UserEntity
import com.kurianski.komidinhas.domain.User
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