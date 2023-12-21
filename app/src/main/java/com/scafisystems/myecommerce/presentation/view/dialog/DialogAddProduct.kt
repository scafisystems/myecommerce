package com.scafisystems.myecommerce.presentation.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.DialogAddProductBinding
import com.scafisystems.myecommerce.presentation.viewmodel.OrderViewModel
import com.scafisystems.myecommerce.util.Extensions.isDouble
import com.scafisystems.myecommerce.util.Extensions.isInt
import com.scafisystems.myecommerce.util.MoneyTextWatcher

class DialogAddProduct(
    context: Context,
    viewModel: OrderViewModel
) : Dialog(context) {

    init {

        val binding: DialogAddProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.dialog_add_product,
            null,
            false
        )

        val moneyTextWatcher = MoneyTextWatcher(binding.editProductValue)

        binding.editProductValue.addTextChangedListener(moneyTextWatcher)

        binding.btnProductAdd.setOnClickListener {
            with(binding) {
                if (editProductName.text.isEmpty() ||
                    editProductQuantidade.text.isEmpty() ||
                    editProductValue.text.isEmpty()
                ) {
                    Toast.makeText(
                        it.context,
                        context.getString(R.string.todos_os_campos_devem_ser_preenchidos),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                if (editProductQuantidade.text.toString().isInt().not()) {
                    Toast.makeText(
                        it.context,
                        context.getString(R.string.no_int),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                if (moneyTextWatcher.getClearNumber().isDouble().not()) {
                    Toast.makeText(
                        it.context,
                        context.getString(R.string.no_double),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                viewModel.addProduct(
                    editProductName.text.toString(),
                    editProductQuantidade.text.toString(),
                    moneyTextWatcher.getClearNumber()
                )
            }

            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        setContentView(binding.root)
    }
}