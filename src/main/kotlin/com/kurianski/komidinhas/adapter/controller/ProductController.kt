package com.kurianski.komidinhas.adapter.controller

import com.kurianski.komidinhas.adapter.mapper.toProductResponse
import com.kurianski.komidinhas.adapter.controller.entity.response.ProductResponse
import com.kurianski.komidinhas.application.usecase.CreateProduct
import com.kurianski.komidinhas.application.usecase.GetProduct
import com.kurianski.komidinhas.domain.product.CreateProductRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val createProduct: CreateProduct,
    private val getProduct: GetProduct
) {

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: UUID): ResponseEntity<Any> {
        val product = getProduct.execute(id)?.toProductResponse()
        return if (product != null) ResponseEntity.ok(product) else ResponseEntity.notFound().build()
    }

    @PostMapping("/")
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest): ResponseEntity<ProductResponse> {
        val product = createProduct.execute(createProductRequest)
        return ResponseEntity(product.toProductResponse(), HttpStatus.CREATED)
    }
}