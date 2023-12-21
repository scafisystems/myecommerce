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
import com.scafisystems.myecommerce.databinding.FragmentNewOrderBinding
import com.scafisystems.myecommerce.ui.model.ProductItem
import com.scafisystems.myecommerce.ui.view.adapter.ProductAdapter
import com.scafisystems.myecommerce.ui.view.dialog.DialogAddProduct
import com.scafisystems.myecommerce.ui.view.navigation.OrderNavigationManager
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel
import com.scafisystems.myecommerce.util.Extensiona.toFormatString

class NewOrderFragment : Fragment() {

    private lateinit var binding: FragmentNewOrderBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var productAdapter: ProductAdapter
    private lateinit var navigationManager: OrderNavigationManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_order, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)
        navigationManager = MyApplication.di.navigationInjection(childFragmentManager)

        setupViews()
        setupObservers()

    }

    private fun setupViews() {
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
            binding.tvOrderTotalValue.text = " R$ ${viewModel.totalProductsValue()?.toFormatString()}"
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