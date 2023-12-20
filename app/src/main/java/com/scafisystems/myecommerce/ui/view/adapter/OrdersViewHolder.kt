package com.scafisystems.myecommerce.ui.view.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.myecommerce.R

class OrdersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val clientName: TextView = itemView.findViewById(R.id.client_name)
    val itemsQuantity: TextView = itemView.findViewById(R.id.orders_quantity)
    val ordersTotalValue: TextView = itemView.findViewById(R.id.order_total_value)
}