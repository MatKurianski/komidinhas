package com.kurianski.komidinhas.adapter.service

import com.kurianski.komidinhas.adapter.controller.entity.AuthenticatedUser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*


@Service
class TokenService {
    @Value("1000000")
    private val expiration: String? = null

    @Value("comidinhas")
    private val secret: String? = null

    fun getUsernameFromToken(token: String): Result<String> =
        runCatching {
            val claims: Claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
            claims.subject
        }

    fun generateToken(authentication: Authentication): String? {
        val logado: AuthenticatedUser = authentication.getPrincipal() as AuthenticatedUser
        val hoje = Date()
        val dataExpiracao = Date(hoje.getTime() + expiration!!.toLong())
        return Jwts.builder()
            .setIssuer("API do Comidinhas!")
            .setSubject(logado.username)
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun isValidToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }
}