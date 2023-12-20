package com.scafisystems.myecommerce

import android.app.Application
import android.content.Context
import com.scafisystems.myecommerce.framework.di.MyDI

//import androidx.room.Room
//import com.scafisystems.myecommerce.database.room.AppDatabase

class MyApplication : Application() {

    companion object {
       // lateinit var database: AppDatabase
        lateinit var di : MyDI
        lateinit var myApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        di = MyDI(this)
        myApplicationContext = applicationContext

        /*database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "my_database"
        ).build()*/
    }
}