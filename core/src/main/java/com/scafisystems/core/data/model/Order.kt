package com.scafisystems.core.data.model


data class Order(
    var id: Long = 0,
    val client: String,
    val products: List<Product>,
    val itemsTotal: Int,
    val totalOrder: Double
)
