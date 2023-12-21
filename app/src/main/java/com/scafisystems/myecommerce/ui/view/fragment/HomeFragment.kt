package com.scafisystems.myecommerce.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.scafisystems.myecommerce.MyApplication
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.FragmentHomeBinding
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel
import com.scafisystems.myecommerce.util.Extensiona.toFormatString


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MyApplication.di.orderViewModelInjection(this)

        setupViews()
        setupObservers()

    }

    override fun onResume() {
        super.onResume()
        updateTextValue()
    }

    private fun setupViews() {
        binding.btnNewOrder.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, NewOrderFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.btnAllOrder.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AllOrdersFragment())
                .addToBackStack(null)
                .commit()
        }
    }


    private fun setupObservers() {
        viewModel.orders.observe(viewLifecycleOwner) {
            updateTextValue()
        }
    }

    private fun updateTextValue() {
        binding.tvTotalValue.text = "${resources.getText(R.string.main_total_sell_value)} ${
            viewModel.totalOrdersValue()?.toFormatString()
        }"
    }
}