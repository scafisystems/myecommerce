package com.scafisystems.core.data.repository

import com.scafisystems.core.data.model.Order

class OrderRepository(private val orderDataSource: OrderDataSource) {

    suspend fun saveOrder(order: Order) {
        orderDataSource.saveOrder(order)
    }

    suspend fun getAllOrders(): List<Order> {
        return orderDataSource.getAllOrders()
    }

    suspend fun deleteOrder(orderId: Long) {
        orderDataSource.deleteOrder(orderId)
    }

    suspend fun getOrder(orderId: Long): Order {
        return orderDataSource.getOrder(orderId)
    }
}