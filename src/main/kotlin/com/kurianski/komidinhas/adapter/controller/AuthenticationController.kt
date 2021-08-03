package com.kurianski.komidinhas.adapter.controller

import com.kurianski.komidinhas.adapter.controller.entity.LoginRequest
import com.kurianski.komidinhas.adapter.controller.entity.response.DefaultResponse
import com.kurianski.komidinhas.adapter.controller.entity.response.TokenResponse
import com.kurianski.komidinhas.adapter.mapper.toErrorResponseEntity
import com.kurianski.komidinhas.adapter.service.TokenService
import com.kurianski.komidinhas.application.usecase.user.CreateUser
import com.kurianski.komidinhas.domain.user.CreateUserRequest
import io.micrometer.core.annotation.Counted
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthenticationController(
    private val createUser: CreateUser,
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping("/signup")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<Any> =
        createUser.execute(createUserRequest).fold(
            onSuccess = { ResponseEntity.status(HttpStatus.CREATED).body(it) },
            onFailure = { it.toErrorResponseEntity() }
        )

    @PostMapping("/login")
    @Counted(value = "login")
    fun getUserAuthToken(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val loginData = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)

        return try {
            val authentication: Authentication = authenticationManager.authenticate(loginData)
            val token: String? = tokenService.generateToken(authentication)
            ResponseEntity.ok(TokenResponse("Bearer", token!!))
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().body(DefaultResponse("Credenciais inválidas!"))
        } catch (e: Exception) {
            e.toErrorResponseEntity()
        }
    }
}