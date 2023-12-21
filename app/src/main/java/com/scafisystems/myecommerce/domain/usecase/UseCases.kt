package com.scafisystems.myecommerce.domain.usecase

import com.scafisystems.core.domain.usecase.DeleteOrderUseCase
import com.scafisystems.core.domain.usecase.GetAllOrdersUseCase
import com.scafisystems.core.domain.usecase.GetAllProductsUseCase
import com.scafisystems.core.domain.usecase.GetOrderUseCase
import com.scafisystems.core.domain.usecase.SaveOrderUseCase
import com.scafisystems.core.domain.usecase.SaveProductUseCase

class UseCases(
    val getAllOrdersUseCase: GetAllOrdersUseCase,
    val saveOrderUseCase: SaveOrderUseCase,
    val getAllProductsUseCase: GetAllProductsUseCase,
    val saveProductUseCase: SaveProductUseCase,
    val deleteOrderUseCase: DeleteOrderUseCase,
    val getOrderUseCase: GetOrderUseCase
)