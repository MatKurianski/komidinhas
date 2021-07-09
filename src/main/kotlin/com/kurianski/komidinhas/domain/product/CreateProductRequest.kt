package com.kurianski.komidinhas.domain.product

import java.math.BigDecimal
import java.util.*

data class CreateProductRequest (
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
)
