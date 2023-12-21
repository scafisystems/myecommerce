package com.scafisystems.myecommerce

import android.app.Application
import android.content.Context
import com.scafisystems.myecommerce.di.MyDI


class MyApplication : Application() {

    companion object {
        lateinit var di: MyDI
        lateinit var myApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        di = MyDI(this)
        myApplicationContext = applicationContext
    }
}