package com.kurianski.komidinhas.application.usecase

import com.kurianski.komidinhas.application.datastore.ProductRepository
import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import org.springframework.stereotype.Component

@Component
class CreateProduct (private val productRepository: ProductRepository) {
    fun execute(createProductRequest: CreateProductRequest): Product =
        productRepository.addProduct(createProductRequest)
}