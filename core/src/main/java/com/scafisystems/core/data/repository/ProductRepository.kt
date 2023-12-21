package com.scafisystems.core.data.repository

import com.scafisystems.core.data.model.Product

class ProductRepository(private val productDataSource: ProductDataSource) {

    suspend fun saveProduct(product: Product) {
        productDataSource.saveProduct(product)
    }

    suspend fun getAllProducts(): List<Product> {
        return productDataSource.getAllProduct()
    }

    suspend fun deleteProduct(productId: Long) {
        productDataSource.deleteProduct(productId)
    }

    suspend fun getProduct(productId: Long): Product {
        return productDataSource.getProduct(productId)
    }
}