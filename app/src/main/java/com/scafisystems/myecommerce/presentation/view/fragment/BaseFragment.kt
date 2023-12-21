package com.scafisystems.myecommerce.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.presentation.view.navigation.OrderNavigationManager
import com.scafisystems.myecommerce.presentation.viewmodel.OrderViewModel

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    protected lateinit var viewModel: OrderViewModel
    protected lateinit var navigationManager: OrderNavigationManager
    protected lateinit var binding: T

    abstract val layoutResourceId: Int
    abstract fun setupViews()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)
        navigationManager = MyApplication.di.navigationInjection(childFragmentManager)

        setupViews()

    }
}