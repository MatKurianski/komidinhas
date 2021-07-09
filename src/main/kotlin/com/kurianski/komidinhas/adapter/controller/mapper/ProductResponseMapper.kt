package com.kurianski.komidinhas.adapter.controller.mapper

import com.kurianski.komidinhas.adapter.controller.response.ProductResponse
import com.kurianski.komidinhas.domain.product.Product

fun Product.toProductResponse(): ProductResponse =
    ProductResponse(
        id.toString(),
        name,
        description,
        price.setScale(2.coerceAtLeast(price.scale())),
        imageUrl
    )