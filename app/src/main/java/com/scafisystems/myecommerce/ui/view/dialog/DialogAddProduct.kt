package com.scafisystems.myecommerce.ui.view.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.scafisystems.myecommerce.R
import com.scafisystems.myecommerce.databinding.DialogAddProductBinding
import com.scafisystems.myecommerce.ui.viewmodel.OrderViewModel

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

        binding.btnProductAdd.setOnClickListener {
            with(binding) {
                if( editProductName.text.isEmpty() ||
                editProductQuantidade.text.isEmpty() ||
                editProductValue.text.isEmpty()){
                    Toast.makeText(it.context, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.addProduct(
                    editProductName.text.toString(),
                    editProductQuantidade.text.toString(),
                    editProductValue.text.toString()
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