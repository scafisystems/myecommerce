package com.scafisystems.core.data.repository

import com.scafisystems.core.data.model.Product

interface ProductDataSource {

    suspend fun saveProduct(product: Product)
    suspend fun getAllProduct(): List<Product>
    suspend fun deleteProduct(productId: Long)
    suspend fun getProduct(productId: Long): Product
}