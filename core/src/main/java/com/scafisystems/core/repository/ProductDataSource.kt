package com.scafisystems.core.repository

import com.scafisystems.core.data.Product

interface ProductDataSource {

    suspend fun saveProduct(product: Product)
    suspend fun getAllProduct(): List<Product>
    suspend fun deleteProduct(productId: Long)
    suspend fun getProduct(productId: Long): Product
}