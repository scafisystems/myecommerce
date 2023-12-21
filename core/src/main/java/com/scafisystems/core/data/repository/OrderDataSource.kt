package com.scafisystems.core.data.repository

import com.scafisystems.core.data.model.Order

interface OrderDataSource {

    suspend fun saveOrder(order: Order)
    suspend fun getAllOrders(): List<Order>
    suspend fun deleteOrder(orderId: Long)
    suspend fun getOrder(orderId: Long): Order
}