package com.scafisystems.core.data


data class Order(
    val id: Long = 0,
    val client: String,
    val products: List<Product>,
    val itemsTotal: Int,
    val totalOrder: Double
)
