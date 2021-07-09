package com.kurianski.komidinhas.adapter.datastore

import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Component
class InMemoryProductRepository : ProductRepository {
    val products: MutableMap<UUID, Product> = ConcurrentHashMap()

    override fun addProduct(createProductRequest: CreateProductRequest): Product {
        val productId = UUID.randomUUID()

        val product = createProductRequest.let {
            Product(productId, it.name, it.description, it.price, it.imageUrl)
        }

        products[productId] = product
        return product
    }

    override fun getById(id: UUID): Product? = products[id]

    override fun getAll(): List<Product> = products.values.toList()
}