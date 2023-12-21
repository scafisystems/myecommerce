package com.scafisystems.myecommerce.presentation.view.fragment

import android.os.Bundle
import android.view.View
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentHomeBinding
import com.scafisystems.myecommerce.presentation.view.navigation.Destinations
import com.scafisystems.myecommerce.util.Extensions.toFormatString


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val layoutResourceId = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onResume() {
        super.onResume()
        updateTextValue()
    }

    override fun setupViews() {
        binding.btnNewOrder.setOnClickListener {
            navigationManager.navigateTo(Destinations.NEW_ORDER)
        }

        binding.btnAllOrder.setOnClickListener {
            navigationManager.navigateTo(Destinations.ALL_ORDERS)
        }
    }


    private fun setupObservers() {
        viewModel.orders.observe(viewLifecycleOwner) {
            updateTextValue()
        }
    }

    private fun updateTextValue() {
        binding.tvTotalValue.text = "${resources.getText(R.string.main_total_sell_value)} ${
            viewModel.totalOrdersValue()?.toFormatString()
        }"
    }
}