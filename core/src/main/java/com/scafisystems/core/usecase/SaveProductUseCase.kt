package com.scafisystems.core.usecase

import com.scafisystems.core.data.Product
import com.scafisystems.core.repository.ProductRepository

class SaveProductUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(product: Product) {
        productRepository.saveProduct(product)
    }
}