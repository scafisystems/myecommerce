package com.scafisystems.myecommerce.ui.model

import com.scafisystems.core.data.Order
import com.scafisystems.myecommerce.util.Extensiona.toFormatString

data class OrdersItem(
    val id: Long,
    val clientName: String,
    val itemsQuantity: Int,
    val ordersTotalValue: String
) {
    companion object {

        fun mapFromOrder(order: Order): OrdersItem {
            return OrdersItem(
                order.id,
                order.client,
                order.products.size,
                order.totalOrder.toFormatString()
            )
        }
    }

}
