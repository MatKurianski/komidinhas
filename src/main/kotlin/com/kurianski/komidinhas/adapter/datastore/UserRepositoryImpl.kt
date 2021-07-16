package com.kurianski.komidinhas.adapter.datastore

import com.kurianski.komidinhas.adapter.controller.mapper.toUser
import com.kurianski.komidinhas.adapter.controller.mapper.toUserEntity
import com.kurianski.komidinhas.application.datastore.UserRepository
import com.kurianski.komidinhas.domain.User
import com.kurianski.komidinhas.domain.exception.DatastoreException
import com.kurianski.komidinhas.domain.exception.UserAlreadyExists
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import org.springframework.stereotype.Component
import java.lang.NullPointerException

@Component
class UserRepositoryImpl(private val userPostgresRepository: UserPostgresRepository) : UserRepository {
    override fun createUser(createUserRequest: CreateUserRequest): Result<User> =
        runCatching {
            if (userPostgresRepository.existsById(createUserRequest.username))
                throw UserAlreadyExists("Usuário já existe!")

            userPostgresRepository.save(createUserRequest.toUserEntity()).toUser()
        }.onFailure {
            when (it) {
                is UserAlreadyExists -> Result.failure<User>(it)
                else -> Result.failure<User>(exception = DatastoreException(it))
            }
        }
}