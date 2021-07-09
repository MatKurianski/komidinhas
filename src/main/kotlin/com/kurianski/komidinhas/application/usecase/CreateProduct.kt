package com.kurianski.komidinhas.application.usecase

import com.kurianski.komidinhas.domain.product.Product
import com.kurianski.komidinhas.domain.product.CreateProductRequest

interface CreateProduct {
    fun execute(createProductRequest: CreateProductRequest): Product;
}