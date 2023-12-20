package com.scafisystems.myecommerce.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scafisystems.myecommerce.framework.db.entity.ProductEntity

@Database(
    entities = [ProductEntity::class/*, OrderEntity::class, OrderProductCrossRef::class*/],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {
    abstract fun orderMakerDao(): OrderMakerDao

    companion object {
        private const val DATABASE_NAME = "order_maker.db"

        @Volatile
        private var instance: DatabaseService? = null


        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }
}