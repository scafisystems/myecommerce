package com.scafisystems.myecommerce.di

import android.app.Application
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.scafisystems.core.repository.OrderRepository
import com.scafisystems.core.repository.ProductRepository
import com.scafisystems.core.usecase.DeleteOrderUseCase
import com.scafisystems.core.usecase.GetAllOrdersUseCase
import com.scafisystems.core.usecase.GetAllProductsUseCase
import com.scafisystems.core.usecase.GetOrderUseCase
import com.scafisystems.core.usecase.SaveOrderUseCase
import com.scafisystems.core.usecase.SaveProductUseCase
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.usecase.UseCases
import com.scafisystems.myecommerce.db.DatabaseManager
import com.scafisystems.myecommerce.db.OrderAndProductDatasource
import com.scafisystems.myecommerce.ui.view.navigation.OrderNavigationManager
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

class MyDI(private val application: Application) {

    private lateinit var useCases: UseCases
    private lateinit var orderRepository: OrderRepository
    private lateinit var productRepository: ProductRepository
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var db: DatabaseManager
    private lateinit var orderAndProductDatasource: OrderAndProductDatasource
    private lateinit var orderNavigationManager: OrderNavigationManager

    fun orderViewModelInjection(owner: ViewModelStoreOwner): OrderViewModel {
        if (!::orderViewModel.isInitialized) {
            orderViewModel = ViewModelProvider(owner)[OrderViewModel::class.java]
        }
        return orderViewModel
    }

    fun useCasesInjection(): UseCases {
        if (!::orderViewModel.isInitialized) {
            useCases = UseCases(
                GetAllOrdersUseCase(orderRepositoryInjection()),
                SaveOrderUseCase(orderRepositoryInjection()),
                GetAllProductsUseCase(productRepositoryInjection()),
                SaveProductUseCase(productRepositoryInjection()),
                DeleteOrderUseCase(orderRepositoryInjection()),
                GetOrderUseCase(orderRepositoryInjection())
            )
        }
        return useCases
    }

    fun orderRepositoryInjection(): OrderRepository {
        if (!::orderRepository.isInitialized) {
            orderRepository = OrderRepository(orderAndProductDatasourceInjection())
        }
        return orderRepository
    }

    fun productRepositoryInjection(): ProductRepository {
        if (!::productRepository.isInitialized) {
            productRepository = ProductRepository(orderAndProductDatasourceInjection())
        }
        return productRepository
    }

    fun dbInjection(): DatabaseManager {
        if (!::db.isInitialized) {
            db = DatabaseManager.getInstance(application)
        }
        return db
    }

    fun orderAndProductDatasourceInjection(): OrderAndProductDatasource {
        if (!::orderAndProductDatasource.isInitialized) {
            orderAndProductDatasource = OrderAndProductDatasource(dbInjection())
        }
        return orderAndProductDatasource
    }

    fun navigationInjection(fragmentManager: FragmentManager): OrderNavigationManager {
        if (!::orderNavigationManager.isInitialized) {
            orderNavigationManager = OrderNavigationManager(fragmentManager, R.id.fragmentContainer)
        }
        return orderNavigationManager
    }

}