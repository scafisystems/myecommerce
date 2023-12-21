package com.scafisystems.core.domain.usecase

import com.scafisystems.core.data.model.Product
import com.scafisystems.core.data.repository.ProductRepository

class GetAllProductsUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(): List<Product> {
        return productRepository.getAllProducts()
    }
}