package com.scafisystems.myecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.scafisystems.core.data.Order
import com.scafisystems.core.data.Product
import com.scafisystems.myecommerce.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val _orders = MutableLiveData<MutableList<Order>>().apply {
        CoroutineScope(Dispatchers.Main).launch {
            val orderList = useCases.getAllOrdersUseCase.invoke()
            value = orderList as MutableList<Order>
        }
    }
    val orders: LiveData<MutableList<Order>> = _orders

    private val _currentProductList =
        MutableLiveData<MutableList<Product>>().apply { value = mutableListOf() }
    val currentProductList: LiveData<MutableList<Product>> = _currentProductList

    private val useCases = MyApplication.di.useCasesInjection()

    private lateinit var orderSelected: Order

    fun getNextOrderNumber(): Long {
        if (_orders.value.isNullOrEmpty().not())
            return orders.value?.maxOf { it.id }?.plus(1L) ?: 1L
        return 1L
    }

    fun saveOrder(client: String) {
        val order = Order(
            getNextOrderNumber(),
            client,
            _currentProductList.value!!.toList(),
            totalProductsQuantity()!!,
            totalProductsValue()!!
        )
        _orders.value?.add(order)
        _currentProductList.value = mutableListOf()

        CoroutineScope(Dispatchers.IO).launch {
            useCases.saveOrderUseCase.invoke(order)
        }

    }

    fun deleteOrder() {
        _orders.value?.remove(orderSelected)
        CoroutineScope(Dispatchers.IO).launch {
            useCases.deleteOrderUseCase.invoke(orderSelected)
        }
    }

    fun addProduct(name: String, quantity: String, unitValue: String) {
        val quantityInt: Int? = quantity.toIntOrNull()
        val unitValueDouble: Double? = unitValue.toDoubleOrNull()
        if (quantityInt != null && unitValueDouble != null) {
            val list = currentProductList.value
            val newProduct = Product(0, name, quantityInt, unitValueDouble)
            list?.add(newProduct)
            _currentProductList.value = list
        }


    }

    fun totalProductsQuantity(): Int? = _currentProductList.value?.toList()?.sumOf { it.quantity }
    fun totalProductsValue(): Double? =
        _currentProductList.value?.toList()?.sumOf { it.getProductTotal() }

    fun totalOrdersValue(): Double? = _orders.value?.toList()?.sumOf { it.totalOrder }

    fun getSelectedOrder(): Order {
        return orderSelected
    }

    fun setSelectedOrder(orderId: Int) {
        orderSelected = _orders.value!![orderId]
    }
}