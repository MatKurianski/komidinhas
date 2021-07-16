package com.kurianski.komidinhas.adapter.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id val username: String,
    val password: String,
    val email: String,
    val created_on: LocalDateTime
) {
    constructor() : this("", "", "", LocalDateTime.now())
}