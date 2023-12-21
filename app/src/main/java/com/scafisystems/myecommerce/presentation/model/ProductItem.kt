package com.scafisystems.myecommerce.presentation.model

import com.scafisystems.core.data.model.Product

data class ProductItem(
    val productName: String,
    val productQuantity: Int,
    val productUnityValue: String,
    val productTotalValue: String
) {
    companion object {

        fun mapFromProduct(product: Product): ProductItem {
            return ProductItem(
                product.name,
                product.quantity,
                product.unitValue.toString(),
                product.getProductTotal().toString()
            )
        }
    }

}
