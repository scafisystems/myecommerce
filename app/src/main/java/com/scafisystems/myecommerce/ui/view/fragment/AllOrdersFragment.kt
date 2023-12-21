package com.scafisystems.myecommerce.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentAllOrdersBinding
import com.scafisystems.myecommerce.ui.model.OrdersItem
import com.scafisystems.myecommerce.ui.view.adapter.OnOrderItemClickListener
import com.scafisystems.myecommerce.ui.view.adapter.OrdersAdapter
import com.scafisystems.myecommerce.ui.view.navigation.Destinations
import com.scafisystems.myecommerce.ui.view.navigation.OrderNavigationManager
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel


class AllOrdersFragment : Fragment() {

    private lateinit var binding: FragmentAllOrdersBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var orderAdapter: OrdersAdapter
    private lateinit var navigationManager: OrderNavigationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_orders, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)
        navigationManager = MyApplication.di.navigationInjection(childFragmentManager)

        setupViews()
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

    private fun setupViews() {
        binding.tvNumberOrders.text =
            "${resources.getString(R.string.orders_number_orders)} ${viewModel.orders.value?.size ?: "0"}"
        setupRecyclerview(viewModel.orders.value?.map { OrdersItem.mapFromOrder(it) }
            ?: emptyList())
    }


}