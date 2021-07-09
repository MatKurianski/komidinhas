package com.kurianski.komidinhas.adapter.controller

import com.kurianski.komidinhas.adapter.controller.mapper.toProductResponse
import com.kurianski.komidinhas.adapter.controller.response.ProductResponse
import com.kurianski.komidinhas.application.usecase.CreateProduct
import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/product")
class ProductController(
    val createProduct: CreateProduct
) {

    @PostMapping
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest): ResponseEntity<ProductResponse> {
        val product = createProduct.execute(createProductRequest)
        return ResponseEntity(product.toProductResponse(), HttpStatus.CREATED)
    }
}