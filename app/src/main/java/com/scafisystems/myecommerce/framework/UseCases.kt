package com.scafisystems.myecommerce.framework

import com.scafisystems.core.usecase.GetAllOrdersUseCase
import com.scafisystems.core.usecase.GetAllProductsUseCase
import com.scafisystems.core.usecase.SaveOrderUseCase
import com.scafisystems.core.usecase.SaveProductUseCase

class UseCases(
        val getAllOrdersUseCase: GetAllOrdersUseCase,
        val saveOrderUseCase: SaveOrderUseCase,
        val getAllProductsUseCase: GetAllProductsUseCase,
        val saveProductUseCase: SaveProductUseCase
)