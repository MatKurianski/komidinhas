package com.kurianski.komidinhas.application.usecase

import com.kurianski.komidinhas.adapter.datastore.InMemoryProductRepository
import com.kurianski.komidinhas.adapter.datastore.ProductRepository
import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import org.springframework.stereotype.Component

@Component
class CreateProductImpl (val productRepository: ProductRepository) : CreateProduct {
    override fun execute(createProductRequest: CreateProductRequest): Product =
        productRepository.addProduct(createProductRequest)
}