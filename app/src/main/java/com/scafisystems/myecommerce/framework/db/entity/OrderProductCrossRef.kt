package com.scafisystems.myecommerce.framework.db.entity

import androidx.room.Entity

@Entity(tableName = "order_product_cross_ref", primaryKeys = ["orderId", "productId"])
data class OrderProductCrossRef(
    val orderId: Long,
    val productId: Long
)