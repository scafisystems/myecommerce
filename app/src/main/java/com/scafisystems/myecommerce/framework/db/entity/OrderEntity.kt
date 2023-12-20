package com.scafisystems.myecommerce.framework.db.entity
/*
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.scafisystems.core.data.Order

@Entity(tableName = "orders")
class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var client: String,
    var products: List<Long>, // Lista de IDs dos produtos associados
    var itemsTotal: Int,
    var totalOrder: Double
) {
    //        fun mapToOrder(): Order {
    //        return Order(id, client, products, itemsTotal, totalOrder)
    //    }

    companion object {
        fun mapToOderEntity(order: Order): OrderEntity {
            return OrderEntity(
                order.id,
                order.client,
                order.products.map { it.id },
                order.itemsTotal,
                order.totalOrder
            )
        }
    }
}*/