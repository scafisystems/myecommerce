package com.scafisystems.core.usecase

import com.scafisystems.core.data.Order
import com.scafisystems.core.repository.OrderRepository

class GetOrderUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(orderId: Long): Order {
        return orderRepository.getOrder(orderId)
    }
}