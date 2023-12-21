package com.scafisystems.myecommerce.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.ui.view.navigation.OrderNavigationManager
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

open class BaseFragment: Fragment() {

    protected lateinit var viewModel: OrderViewModel
    protected lateinit var navigationManager: OrderNavigationManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)
        navigationManager = MyApplication.di.navigationInjection(childFragmentManager)

    }
}