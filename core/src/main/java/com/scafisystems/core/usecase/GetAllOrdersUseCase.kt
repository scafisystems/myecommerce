package com.scafisystems.core.usecase

import com.scafisystems.core.data.Order
import com.scafisystems.core.repository.OrderRepository

class GetAllOrdersUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(): List<Order> {
        return orderRepository.getAllOrders()
    }
}