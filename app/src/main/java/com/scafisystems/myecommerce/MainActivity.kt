package com.scafisystems.myecommerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.scafisystems.myecommerce.databinding.ActivityMainBinding
import com.scafisystems.myecommerce.presentation.view.navigation.Destinations
import com.scafisystems.myecommerce.presentation.view.navigation.OrderNavigationManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationManager: OrderNavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navigationManager = MyApplication.di.navigationInjection(supportFragmentManager)

        navigationManager.navigateTo(Destinations.HOME)

    }


}