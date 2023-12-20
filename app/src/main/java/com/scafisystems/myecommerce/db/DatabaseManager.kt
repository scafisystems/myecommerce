package com.scafisystems.myecommerce.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "orders_database"
        private const val DATABASE_VERSION = 1

        private var instance: DatabaseManager? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseManager {
            if (instance == null) {
                instance = DatabaseManager(context.applicationContext)
            }
            return instance as DatabaseManager
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(OrderTable.CREATE_TABLE)
        db.execSQL(ProductTable.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${OrderTable.TABLE_NAME}")
        db.execSQL("DROP TABLE IF EXISTS ${ProductTable.TABLE_NAME}")
        onCreate(db)
    }

}

object OrderTable {
    const val TABLE_NAME = "orders"
    const val COLUMN_ID = "id"
    const val COLUMN_CLIENT = "client"
    const val COLUMN_ITEMS_TOTAL = "items_total"
    const val COLUMN_TOTAL_ORDER = "total_order"

    const val CREATE_TABLE = """
        CREATE TABLE IF NOT EXISTS $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_CLIENT TEXT NOT NULL,
            $COLUMN_ITEMS_TOTAL INTEGER NOT NULL,
            $COLUMN_TOTAL_ORDER REAL NOT NULL
        )
    """
}

object ProductTable {
    const val TABLE_NAME = "products"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_QUANTITY = "quantity"
    const val COLUMN_UNIT_VALUE = "unitValue"
    const val COLUMN_ORDER_ID = "orderId"

    const val CREATE_TABLE =
        "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_QUANTITY INTEGER," +
                "$COLUMN_UNIT_VALUE REAL," +
                "$COLUMN_ORDER_ID INTEGER," +
                "FOREIGN KEY ($COLUMN_ORDER_ID) REFERENCES ${OrderTable.TABLE_NAME}(${OrderTable.COLUMN_ID})" +
                ")"
}