package com.scafisystems.myecommerce.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference
import java.text.NumberFormat
import java.util.*

class MoneyTextWatcher(editText: EditText) : TextWatcher {
    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)
    private val currencyFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

    init {
        currencyFormat.maximumFractionDigits = 2
        currencyFormat.isParseIntegerOnly = false
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(editable: Editable?) {
        val editText = editTextWeakReference.get() ?: return
        editText.removeTextChangedListener(this)

        val cleanString = editable.toString().replace("[R$,.\\s]".toRegex(), "")
        val parsed = cleanString.toDoubleOrNull() ?: 0.0

        val formatted = currencyFormat.format(parsed / 100)

        editText.setText(formatted)
        editText.setSelection(formatted.length)

        editText.addTextChangedListener(this)
    }

    fun getClearNumber(): String {
        val editText = editTextWeakReference.get() ?: return "0.0"
        val text = editText.text.toString().replace("[R$\\s]".toRegex(), "")
        return text.replace("[,\\s]".toRegex(), ".")
    }

}