package com.scafisystems.core.domain.usecase

import com.scafisystems.core.data.model.Order
import com.scafisystems.core.data.repository.OrderRepository

class GetOrderUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(orderId: Long): Order {
        return orderRepository.getOrder(orderId)
    }
}