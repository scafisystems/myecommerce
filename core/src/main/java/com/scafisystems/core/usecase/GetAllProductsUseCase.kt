package com.scafisystems.core.usecase

import com.scafisystems.core.data.Product
import com.scafisystems.core.repository.ProductRepository

class GetAllProductsUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): List<Product> {
        return productRepository.getAllProducts()
    }
}