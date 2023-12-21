package com.scafisystems.myecommerce.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.myecommerce.databinding.ProductItemBinding
import com.scafisystems.myecommerce.presentation.model.ProductItem
import com.scafisystems.myecommerce.util.Extensions.removeExcessDecimal


class ProductAdapter(private val productList: List<ProductItem>) :
    RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = " " + product.productName
        holder.productQuantity.text = " " + product.productQuantity.toString()
        holder.productUnityValue.text = "R$ ${product.productUnityValue.removeExcessDecimal()}"
        holder.productTotalValue.text = "R$ ${product.productTotalValue.removeExcessDecimal()}"
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}