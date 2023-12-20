package com.scafisystems.myecommerce.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.myecommerce.databinding.ProductItemBinding
import com.scafisystems.myecommerce.ui.model.ProductItem


class ProductAdapter(private val productList: List<ProductItem>) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.productName
        holder.productQuantity.text = product.productQuantity.toString()
        holder.productUnityValue.text = product.productUnityValue
        holder.productTotalValue.text = product.productTotalValue
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}