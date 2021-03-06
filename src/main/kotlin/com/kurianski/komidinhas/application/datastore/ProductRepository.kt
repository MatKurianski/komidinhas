package com.kurianski.komidinhas.application.datastore

import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import java.util.*

interface ProductRepository {
    fun addProduct(createProductRequest: CreateProductRequest): Product;
    fun getById(id: UUID): Product?;
    fun getAll(): List<Product>
}