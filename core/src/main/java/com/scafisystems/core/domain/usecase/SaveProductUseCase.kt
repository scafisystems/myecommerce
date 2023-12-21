package com.scafisystems.core.domain.usecase

import com.scafisystems.core.data.model.Product
import com.scafisystems.core.data.repository.ProductRepository

class SaveProductUseCase(private val productRepository: ProductRepository) {

    suspend operator fun invoke(product: Product) {
        productRepository.saveProduct(product)
    }
}