package com.scafisystems.myecommerce.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.scafisystems.myecommerce.framework.db.entity.ProductEntity

@Dao
interface OrderMakerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(productEntity: ProductEntity)
}