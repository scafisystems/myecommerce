package com.scafisystems.myecommerce.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.ActivityMainBinding
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = MyApplication.di.orderViewModelInjection(this)

    }

    override fun onResume() {
        super.onResume()

        setupViews()
    }

    private fun setupViews(){
        binding.btnNewOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        binding.tvTotalValue.text = viewModel.totalOrdersValue().toString()
    }
}