package com.kurianski.komidinhas.adapter.bean.security

import com.kurianski.komidinhas.adapter.mapper.toAuthenticatedUser
import com.kurianski.komidinhas.adapter.service.TokenService
import com.kurianski.komidinhas.application.datastore.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationByTokenFilter(
    private val tokenService: TokenService,
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val result: Result<String> = getToken(request)
        result.onSuccess { authenticateUser(it) }
        filterChain.doFilter(request, response)
    }

    private fun authenticateUser(token: String) {
        val result: Result<String> = tokenService.getUsernameFromToken(token)

        result.onSuccess { it ->
            val userResponse = userRepository.getUserCredentials(it)
            val user = userResponse.map { it.toAuthenticatedUser() }

            user.onSuccess {
                val authentication = UsernamePasswordAuthenticationToken(it, null, it.authorities)
                SecurityContextHolder.getContext().authentication = authentication
            }.onFailure {
                throw UsernameNotFoundException("Usuário não encontrado")
            }
        }
    }

    private fun getToken(httpServletRequest: HttpServletRequest): Result<String> =
        runCatching {
            val token: String? = httpServletRequest.getHeader("Authorization")
            if (token.isNullOrBlank() || !token.startsWith("Bearer")) throw IllegalArgumentException()
            else token.substring(7)
        }
}