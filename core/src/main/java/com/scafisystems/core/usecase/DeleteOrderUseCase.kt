package com.scafisystems.core.usecase

import com.scafisystems.core.data.Order
import com.scafisystems.core.repository.OrderRepository

class DeleteOrderUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(order: Order) {
        orderRepository.deleteOrder(order.id)
    }
}