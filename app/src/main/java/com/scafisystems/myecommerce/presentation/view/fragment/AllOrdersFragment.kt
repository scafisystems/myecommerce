package com.scafisystems.myecommerce.presentation.view.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentAllOrdersBinding
import com.scafisystems.myecommerce.presentation.model.OrdersItem
import com.scafisystems.myecommerce.presentation.view.listener.OnOrderItemClickListener
import com.scafisystems.myecommerce.presentation.view.adapter.OrdersAdapter
import com.scafisystems.myecommerce.presentation.view.navigation.Destinations


class AllOrdersFragment : BaseFragment<FragmentAllOrdersBinding>() {

    private lateinit var orderAdapter: OrdersAdapter
    override val layoutResourceId = R.layout.fragment_all_orders

    override fun setupViews() {
        binding.tvNumberOrders.text =
            "${resources.getString(R.string.orders_number_orders)} ${viewModel.orders.value?.size ?: "0"}"
        setupRecyclerview(viewModel.orders.value?.map { OrdersItem.mapFromOrder(it) }
            ?: emptyList())
    }

    private fun setupRecyclerview(orderList: List<OrdersItem>) {
        binding.rvOrdersList.layoutManager = LinearLayoutManager(requireContext())
        orderAdapter = OrdersAdapter(orderList, object : OnOrderItemClickListener {
            override fun onOrderItemClick(position: Int) {
                viewModel.setSelectedOrder(position)
                navigationManager.navigateTo(Destinations.VIEW_ORDER)
            }
        })
        binding.rvOrdersList.adapter = orderAdapter
    }

}