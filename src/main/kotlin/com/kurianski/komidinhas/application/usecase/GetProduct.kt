package com.kurianski.komidinhas.application.usecase

import com.kurianski.komidinhas.application.datastore.ProductRepository
import com.kurianski.komidinhas.domain.product.Product
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetProduct(val productRepository: ProductRepository) {
    fun execute(productId: UUID): Product? = productRepository.getById(productId)
}