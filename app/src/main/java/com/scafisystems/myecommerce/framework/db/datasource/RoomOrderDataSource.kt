package com.scafisystems.myecommerce.framework.db.datasource

import com.scafisystems.core.data.Order
import com.scafisystems.core.repository.OrderDataSource
import com.scafisystems.myecommerce.framework.db.OrderMakerDao

class RoomOrderDataSource(private val orderMakerDao: OrderMakerDao): OrderDataSource {

    override suspend fun saveOrder(order: Order) {
      //  orderMakerDao.saveOrder(OrderEntity.mapToOderEntity(order))
    }

    override suspend fun getAllOrders(): List<Order> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteOrder(orderId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun getOrder(orderId: Long): Order {
        TODO("Not yet implemented")
    }
}