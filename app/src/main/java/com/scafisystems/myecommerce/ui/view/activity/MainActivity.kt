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

        setupViews()
        setupObservers()

    }

    override fun onResume() {
        super.onResume()
        updateTextValue()
    }

    private fun setupViews() {
        binding.btnNewOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        binding.btnAllOrder.setOnClickListener {
            startActivity(Intent(this, AllOrdersActivity::class.java))
        }

    }

    private fun setupObservers() {
        viewModel.orders.observe(this) {
            updateTextValue()
        }
    }

    private fun updateTextValue() {
        binding.tvTotalValue.text = "${resources.getText(R.string.main_total_sell_value)} ${
            viewModel.totalOrdersValue().toString()
        }"
    }
}