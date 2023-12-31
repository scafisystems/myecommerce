package com.scafisystems.core.data.model

data class Product(
    var id: Long = 0,
    val name: String,
    val quantity: Int,
    val unitValue: Double
) {
    fun getProductTotal(): Double = quantity * unitValue
}