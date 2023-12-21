package com.scafisystems.myecommerce.presentation.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.myecommerce.R

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productName: TextView = itemView.findViewById(R.id.product_name)
    val productQuantity: TextView = itemView.findViewById(R.id.product_quantity)
    val productUnityValue: TextView = itemView.findViewById(R.id.product_unity_value)
    val productTotalValue: TextView = itemView.findViewById(R.id.product_total_value)
}