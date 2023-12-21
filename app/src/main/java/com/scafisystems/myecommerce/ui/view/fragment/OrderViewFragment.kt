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
import com.scafisystems.myecommerce.databinding.FragmentOrderViewBinding
import com.scafisystems.myecommerce.ui.model.OrdersItem
import com.scafisystems.myecommerce.ui.model.ProductItem
import com.scafisystems.myecommerce.ui.view.adapter.OnOrderItemClickListener
import com.scafisystems.myecommerce.ui.view.adapter.OrdersAdapter
import com.scafisystems.myecommerce.ui.view.adapter.ProductAdapter
import com.scafisystems.myecommerce.ui.view.dialog.DialogAddProduct
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel
import com.scafisystems.myecommerce.util.Extensiona.toFormatString


class OrderViewFragment : Fragment() {

    private lateinit var binding: FragmentOrderViewBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)

        setupViews()

    }

    private fun setupViews() {
        with(binding) {

            btnOrderDelete.setOnClickListener {
                viewModel.deleteOrder()
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

            btnOrderOk.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
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