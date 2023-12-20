package com.scafisystems.myecommerce.framework.db.datasource

import com.scafisystems.core.data.Product
import com.scafisystems.core.repository.OrderRepository
import com.scafisystems.core.repository.ProductDataSource
import com.scafisystems.core.repository.ProductRepository
import com.scafisystems.myecommerce.framework.db.OrderMakerDao
import com.scafisystems.myecommerce.framework.db.entity.ProductEntity

class RoomProductDataSource(private val orderMakerDao: OrderMakerDao): ProductDataSource {

    override suspend fun saveProduct(product: Product) {
        orderMakerDao.saveProduct(ProductEntity.mapToProductEntity(product))
    }

    override suspend fun getAllProduct(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(productId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getProduct(productId: Long): Product {
        TODO("Not yet implemented")
    }


}