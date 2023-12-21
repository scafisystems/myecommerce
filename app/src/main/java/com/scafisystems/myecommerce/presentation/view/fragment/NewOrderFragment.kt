package com.scafisystems.myecommerce.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentNewOrderBinding
import com.scafisystems.myecommerce.presentation.model.ProductItem
import com.scafisystems.myecommerce.presentation.view.adapter.ProductAdapter
import com.scafisystems.myecommerce.presentation.view.dialog.DialogAddProduct
import com.scafisystems.myecommerce.util.Extensions.toFormatString

class NewOrderFragment : BaseFragment<FragmentNewOrderBinding>() {

    private lateinit var productAdapter: ProductAdapter
    override val layoutResourceId = R.layout.fragment_new_order


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun setupViews() {
        with(binding) {
            btnProductAdd.setOnClickListener {
                DialogAddProduct(this@NewOrderFragment.requireContext(), viewModel).show()
            }

            btnOrderSave.setOnClickListener {
                viewModel.saveOrder(editClientName.text.toString())
                clearFields()
                navigationManager.navigateBack()
            }

            btnOrderCancel.setOnClickListener {
                navigationManager.navigateBack()
            }

            tvOrderNumber.text = " ${viewModel.getNextOrderNumber()}"
        }


    }

    private fun setupObservers() {
        viewModel.currentProductList.observe(viewLifecycleOwner) { products ->
            setupRecyclerview(products.map { ProductItem.mapFromProduct(it) })
            binding.tvOrderTotalItems.text = " ${viewModel.totalProductsQuantity().toString()}"
            binding.tvOrderTotalValue.text =
                " R$ ${viewModel.totalProductsValue()?.toFormatString()}"
        }
    }

    private fun setupRecyclerview(productList: List<ProductItem>) {

        binding.rvProductList.layoutManager = LinearLayoutManager(requireContext())
        productAdapter = ProductAdapter(productList)
        binding.rvProductList.adapter = productAdapter
    }

    private fun clearFields() {
        with(binding) {
            tvOrderTotalItems.text = ""
            tvOrderTotalValue.text = ""
            editClientName.text.clear()
        }

    }

}