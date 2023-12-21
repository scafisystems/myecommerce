package com.scafisystems.myecommerce.presentation.view.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentOrderViewBinding
import com.scafisystems.myecommerce.presentation.model.ProductItem
import com.scafisystems.myecommerce.presentation.view.adapter.ProductAdapter
import com.scafisystems.myecommerce.util.Extensions.toFormatString


class OrderViewFragment : BaseFragment<FragmentOrderViewBinding>() {

    private lateinit var productAdapter: ProductAdapter
    override val layoutResourceId = R.layout.fragment_order_view

    override fun setupViews() {
        with(binding) {

            btnOrderDelete.setOnClickListener {
                viewModel.deleteOrder()
                navigationManager.navigateBack()
            }

            btnOrderOk.setOnClickListener {
                navigationManager.navigateBack()
            }

            with(viewModel.getSelectedOrder()) {
                tvOrderNumber.text = id.toString()
                editClientName.setText(client)
                tvOrderTotalItems.text = itemsTotal.toString()
                tvOrderTotalValue.text = totalOrder.toFormatString()

                setupRecyclerview(products.map { ProductItem.mapFromProduct(it) })
            }

        }

    }

    private fun setupRecyclerview(productList: List<ProductItem>) {
        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())
        productAdapter = ProductAdapter(productList)
        binding.rvProductList.adapter = productAdapter
    }


}