package com.kurianski.komidinhas.adapter.controller.response

import java.math.BigDecimal

data class ProductResponse (
    val id: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String
)