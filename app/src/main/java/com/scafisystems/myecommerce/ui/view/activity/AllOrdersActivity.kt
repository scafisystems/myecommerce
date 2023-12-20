package com.scafisystems.myecommerce.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.ActivityAllOrdersBinding
import com.scafisystems.myecommerce.ui.model.OrdersItem
import com.scafisystems.myecommerce.ui.view.adapter.OrdersAdapter
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class AllOrdersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllOrdersBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var orderAdapter: OrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_orders)
        viewModel = MyApplication.di.orderViewModelInjection(this)

        setupViews()
    }

    private fun setupRecyclerview(orderList: List<OrdersItem>) {
        binding.rvOrdersList.layoutManager = LinearLayoutManager(this)
        orderAdapter = OrdersAdapter(orderList)
        binding.rvOrdersList.adapter = orderAdapter
    }

    private fun setupViews() {
        binding.tvNumberOrders.text =
            "${resources.getString(R.string.orders_number_orders)} ${viewModel.orders.value?.size ?: "0"}"
        setupRecyclerview(viewModel.orders.value?.map { OrdersItem.mapFromOrder(it) }
            ?: emptyList())
    }
}