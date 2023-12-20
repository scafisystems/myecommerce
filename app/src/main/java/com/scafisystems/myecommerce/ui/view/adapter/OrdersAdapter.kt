package com.scafisystems.myecommerce.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scafisystems.myecommerce.databinding.*
import com.scafisystems.myecommerce.ui.model.OrdersItem


class OrdersAdapter(private val ordersList: List<OrdersItem>) :
    RecyclerView.Adapter<OrdersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OrderItemBinding.inflate(inflater, parent, false)
        return OrdersViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val order = ordersList[position]
        holder.clientName.text = order.clientName
        holder.itemsQuantity.text = order.itemsQuantity.toString()
        holder.ordersTotalValue.text = order.ordersTotalValue
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }
}