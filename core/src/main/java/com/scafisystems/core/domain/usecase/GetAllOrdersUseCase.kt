package com.scafisystems.core.domain.usecase

import com.scafisystems.core.data.model.Order
import com.scafisystems.core.data.repository.OrderRepository

class GetAllOrdersUseCase(private val orderRepository: OrderRepository) {

    suspend operator fun invoke(): List<Order> {
        return orderRepository.getAllOrders()
    }
}