package com.scafisystems.myecommerce.ui.model

import com.scafisystems.core.data.Order

data class OrdersItem(
    val clientName: String,
    val itemsQuantity: Int,
    val ordersTotalValue: String
) {
    companion object {

        fun mapFromOrder(order: Order): OrdersItem {
            return OrdersItem(
                order.client,
                order.products.size,
                order.totalOrder.toString()
            )
        }
    }

}
