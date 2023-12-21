package com.scafisystems.core.domain.usecase

import com.scafisystems.core.data.model.Order
import com.scafisystems.core.data.repository.OrderRepository

class SaveOrderUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(order: Order) {
        orderRepository.saveOrder(order)
    }
}