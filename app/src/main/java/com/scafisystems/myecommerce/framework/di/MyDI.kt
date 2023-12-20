package com.scafisystems.myecommerce.framework.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.scafisystems.core.repository.OrderRepository
import com.scafisystems.core.repository.ProductRepository
import com.scafisystems.core.usecase.GetAllOrdersUseCase
import com.scafisystems.core.usecase.GetAllProductsUseCase
import com.scafisystems.core.usecase.SaveOrderUseCase
import com.scafisystems.core.usecase.SaveProductUseCase
import com.scafisystems.myecommerce.framework.UseCases
import com.scafisystems.myecommerce.framework.db.DatabaseService
import com.scafisystems.myecommerce.framework.db.OrderMakerDao
import com.scafisystems.myecommerce.framework.db.datasource.RoomOrderDataSource
import com.scafisystems.myecommerce.framework.db.datasource.RoomProductDataSource
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class MyDI(private val application: Application) {

    private lateinit var useCases: UseCases
    private lateinit var orderRepository: OrderRepository
    private lateinit var productRepository: ProductRepository
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderMakerDao: OrderMakerDao

    fun orderViewModelInjection(owner: ViewModelStoreOwner): OrderViewModel{
        if(!::orderViewModel.isInitialized){
            orderViewModel = ViewModelProvider(owner)[OrderViewModel::class.java]
        }
        return orderViewModel
    }
    fun useCasesInjection(): UseCases{
        if(!::orderViewModel.isInitialized){
            useCases = UseCases(
                GetAllOrdersUseCase(orderRepositoryInjection()),
                SaveOrderUseCase(orderRepositoryInjection()),
                GetAllProductsUseCase(productRepositoryInjection()),
                SaveProductUseCase(productRepositoryInjection())
            )
        }
       return useCases
    }

    fun orderRepositoryInjection(): OrderRepository{
        if(!::orderRepository.isInitialized){
            orderRepository = OrderRepository(RoomOrderDataSource(orderMakerDaoInjection()))
        }
        return orderRepository
    }

    fun productRepositoryInjection(): ProductRepository{
        if(!::productRepository.isInitialized){
            productRepository = ProductRepository(RoomProductDataSource(orderMakerDaoInjection()))
        }
        return productRepository
    }

    fun orderMakerDaoInjection(): OrderMakerDao{
        if(!::orderMakerDao.isInitialized){
            orderMakerDao = DatabaseService.getInstance(application).orderMakerDao()
        }
        return orderMakerDao
    }

}