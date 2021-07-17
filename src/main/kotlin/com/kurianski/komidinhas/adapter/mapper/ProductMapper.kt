package com.kurianski.komidinhas.adapter.mapper

import com.kurianski.komidinhas.adapter.controller.entity.response.ProductResponse
import com.kurianski.komidinhas.domain.product.CreateProductRequest
import com.kurianski.komidinhas.domain.product.Product
import java.util.*

fun Product.toProductResponse(): ProductResponse =
    ProductResponse(
        id.toString(),
        name,
        description,
        price.setScale(2.coerceAtLeast(price.scale())),
        imageUrl
    )

fun CreateProductRequest.toProduct(productId: UUID): Product =
    Product(
        id = productId,
        name,
        description,
        price,
        imageUrl
    )