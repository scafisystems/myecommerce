package com.scafisystems.myecommerce.framework.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scafisystems.core.data.Product

@Entity(tableName = "products")
class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var quantity: Int,
    var unitValue: Double
) {
    fun mapToProduct(): Product {
        return Product(id, name, quantity, unitValue)
    }

    companion object {
        fun mapToProductEntity(product: Product): ProductEntity {
            return ProductEntity(product.id, product.name, product.quantity, product.unitValue)
        }
    }

}