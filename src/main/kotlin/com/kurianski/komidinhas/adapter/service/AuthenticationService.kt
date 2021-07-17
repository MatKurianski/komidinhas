package com.kurianski.komidinhas.adapter.service

import com.kurianski.komidinhas.adapter.mapper.toAuthenticatedUser
import com.kurianski.komidinhas.application.datastore.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
     private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val result = userRepository.getUserCredentials(username)

        return result.fold(
            onSuccess = { it.toAuthenticatedUser() } ,
            onFailure = { throw UsernameNotFoundException("Usuário não existe") }
        )
    }
}