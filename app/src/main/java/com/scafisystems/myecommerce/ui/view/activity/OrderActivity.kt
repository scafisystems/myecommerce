package com.scafisystems.myecommerce.ui.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.ActivityOrderBinding
import com.scafisystems.myecommerce.ui.model.ProductItem
import com.scafisystems.myecommerce.ui.view.adapter.ProductAdapter
import com.scafisystems.myecommerce.ui.view.dialog.DialogAddProduct
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var viewModel: OrderViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order)
        viewModel = MyApplication.di.orderViewModelInjection(this)


        setupViews()
        setupObserves()
    }

    private fun setupViews() {
        with(binding) {
            btnProductAdd.setOnClickListener {
                DialogAddProduct(this@OrderActivity, viewModel).show()
            }

            btnOrderSave.setOnClickListener {
                viewModel.saveOrder(editClientName.toString())
                clearFields()
                onBackPressedDispatcher.onBackPressed()
            }

            btnOrderCancel.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            tvOrderNumber.text = viewModel.getNextOrderNumber().toString()
        }


    }

    private fun setupObserves() {
        viewModel.orders.observe(this) {
            Toast.makeText(this, "${viewModel.orders.value}", Toast.LENGTH_SHORT).show()
        }

        viewModel.currentProductList.observe(this) { products ->
            setupRecyclerview(products.map { ProductItem.mapFromProduct(it) })
            binding.tvOrderTotalItems.text = viewModel.totalProductsQuantity().toString()
            binding.tvOrderTotalValue.text = viewModel.totalProductsValue().toString()
        }
    }

    private fun setupRecyclerview(productList: List<ProductItem>) {

        binding.rvProductList.layoutManager = LinearLayoutManager(this)
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