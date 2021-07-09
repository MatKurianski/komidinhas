package com.kurianski.komidinhas.domain.product

import java.math.BigDecimal
import java.util.*

data class Product (
    val id: UUID,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
)