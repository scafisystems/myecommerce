package com.scafisystems.myecommerce.data.db

import android.annotation.SuppressLint
import android.content.ContentValues
import com.scafisystems.core.data.model.Order
import com.scafisystems.core.data.model.Product
import com.scafisystems.core.data.repository.OrderDataSource
import com.scafisystems.core.data.repository.ProductDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("Range")
class OrderAndProductDatasource(private val db: DatabaseManager) : OrderDataSource,
    ProductDataSource {

    override suspend fun saveOrder(order: Order) = withContext(Dispatchers.IO) {

        val orderValues = ContentValues().apply {
            put(OrderTable.COLUMN_CLIENT, order.client)
            put(OrderTable.COLUMN_ITEMS_TOTAL, order.itemsTotal)
            put(OrderTable.COLUMN_TOTAL_ORDER, order.totalOrder)
        }

        val orderId = db.writableDatabase.insert(OrderTable.TABLE_NAME, null, orderValues)
        order.id = orderId


        for (product in order.products) {
            val productValues = ContentValues().apply {
                put(ProductTable.COLUMN_NAME, product.name)
                put(ProductTable.COLUMN_QUANTITY, product.quantity)
                put(ProductTable.COLUMN_UNIT_VALUE, product.unitValue)
                put(ProductTable.COLUMN_ORDER_ID, orderId)
            }

            val productId = db.writableDatabase.insert(ProductTable.TABLE_NAME, null, productValues)
            product.id = productId
        }
    }


    override suspend fun getAllOrders(): List<Order> = withContext(Dispatchers.IO) {
        val orders = mutableListOf<Order>()

        val query = "SELECT * FROM ${OrderTable.TABLE_NAME}"
        val cursor = db.readableDatabase.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val orderId = it.getLong(it.getColumnIndex(OrderTable.COLUMN_ID))
                val client = it.getString(it.getColumnIndex(OrderTable.COLUMN_CLIENT))
                val itemsTotal = it.getInt(it.getColumnIndex(OrderTable.COLUMN_ITEMS_TOTAL))
                val totalOrder = it.getDouble(it.getColumnIndex(OrderTable.COLUMN_TOTAL_ORDER))

                val order = Order(orderId, client, emptyList(), itemsTotal, totalOrder)
                val products = getAllProductsForOrder(orderId)
                orders.add(order.copy(products = products))
            }
        }

        return@withContext orders
    }

    override suspend fun getOrder(orderId: Long): Order = withContext(Dispatchers.IO) {
        val cursor = db.readableDatabase.query(
            OrderTable.TABLE_NAME,
            null,
            "${OrderTable.COLUMN_ID} = ?",
            arrayOf(orderId.toString()),
            null,
            null,
            null
        )

        cursor.use {
            it.moveToFirst()

            val client = it.getString(it.getColumnIndex(OrderTable.COLUMN_CLIENT))
            val itemsTotal = it.getInt(it.getColumnIndex(OrderTable.COLUMN_ITEMS_TOTAL))
            val totalOrder = it.getDouble(it.getColumnIndex(OrderTable.COLUMN_TOTAL_ORDER))

            val order = Order(orderId, client, emptyList(), itemsTotal, totalOrder)
            val products = getAllProductsForOrder(orderId)
            return@withContext order.copy(products = products)
        }
    }

    override suspend fun deleteOrder(orderId: Long): Unit = withContext(Dispatchers.IO) {
        db.writableDatabase.delete(
            OrderTable.TABLE_NAME,
            "${OrderTable.COLUMN_ID} = ?",
            arrayOf(orderId.toString())
        )
    }

    override suspend fun saveProduct(product: Product) = withContext(Dispatchers.IO) {
        val values = ContentValues().apply {
            put(ProductTable.COLUMN_NAME, product.name)
            put(ProductTable.COLUMN_QUANTITY, product.quantity)
            put(ProductTable.COLUMN_UNIT_VALUE, product.unitValue)
        }

        val productsaveId = db.writableDatabase.insert(ProductTable.TABLE_NAME, null, values)
        product.id = productsaveId
    }

    override suspend fun getAllProduct(): List<Product> = withContext(Dispatchers.IO) {
        val products = mutableListOf<Product>()

        val query = "SELECT * FROM ${ProductTable.TABLE_NAME}"
        val cursor = db.readableDatabase.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val productId = it.getLong(it.getColumnIndex(ProductTable.COLUMN_ID))
                val name = it.getString(it.getColumnIndex(ProductTable.COLUMN_NAME))
                val quantity = it.getInt(it.getColumnIndex(ProductTable.COLUMN_QUANTITY))
                val unitValue = it.getDouble(it.getColumnIndex(ProductTable.COLUMN_UNIT_VALUE))

                val product = Product(productId, name, quantity, unitValue)
                products.add(product)
            }
        }

        return@withContext products
    }

    override suspend fun deleteProduct(productId: Long): Unit = withContext(Dispatchers.IO) {
        db.writableDatabase.delete(
            ProductTable.TABLE_NAME,
            "${ProductTable.COLUMN_ID} = ?",
            arrayOf(productId.toString())
        )
    }

    override suspend fun getProduct(productId: Long): Product = withContext(Dispatchers.IO) {
        val cursor = db.readableDatabase.query(
            ProductTable.TABLE_NAME,
            null,
            "${ProductTable.COLUMN_ID} = ?",
            arrayOf(productId.toString()),
            null,
            null,
            null
        )

        cursor.use {
            it.moveToFirst()

            val name = it.getString(it.getColumnIndex(ProductTable.COLUMN_NAME))
            val quantity = it.getInt(it.getColumnIndex(ProductTable.COLUMN_QUANTITY))
            val unitValue = it.getDouble(it.getColumnIndex(ProductTable.COLUMN_UNIT_VALUE))

            return@withContext Product(productId, name, quantity, unitValue)
        }
    }

    suspend fun getAllProductsForOrder(orderId: Long): List<Product> = withContext(Dispatchers.IO) {
        val products = mutableListOf<Product>()

        val query =
            "SELECT * FROM ${ProductTable.TABLE_NAME} WHERE ${ProductTable.COLUMN_ORDER_ID} = ?"
        val cursor = db.readableDatabase.rawQuery(query, arrayOf(orderId.toString()))

        cursor.use {
            while (it.moveToNext()) {
                val productId = it.getLong(it.getColumnIndex(ProductTable.COLUMN_ID))
                val name = it.getString(it.getColumnIndex(ProductTable.COLUMN_NAME))
                val quantity = it.getInt(it.getColumnIndex(ProductTable.COLUMN_QUANTITY))
                val unitValue = it.getDouble(it.getColumnIndex(ProductTable.COLUMN_UNIT_VALUE))

                val product = Product(productId, name, quantity, unitValue)
                products.add(product)
            }
        }

        return@withContext products
    }

}