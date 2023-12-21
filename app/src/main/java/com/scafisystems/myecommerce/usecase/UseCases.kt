package com.scafisystems.myecommerce.usecase

import com.scafisystems.core.usecase.DeleteOrderUseCase
import com.scafisystems.core.usecase.GetAllOrdersUseCase
import com.scafisystems.core.usecase.GetAllProductsUseCase
import com.scafisystems.core.usecase.GetOrderUseCase
import com.scafisystems.core.usecase.SaveOrderUseCase
import com.scafisystems.core.usecase.SaveProductUseCase

class UseCases(
        val getAllOrdersUseCase: GetAllOrdersUseCase,
        val saveOrderUseCase: SaveOrderUseCase,
        val getAllProductsUseCase: GetAllProductsUseCase,
        val saveProductUseCase: SaveProductUseCase,
        val deleteOrderUseCase: DeleteOrderUseCase,
        val getOrderUseCase: GetOrderUseCase
)