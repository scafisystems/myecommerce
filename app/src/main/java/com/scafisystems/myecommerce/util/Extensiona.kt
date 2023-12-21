package com.scafisystems.myecommerce.util

import java.text.DecimalFormat

object Extensiona {

    fun Double.toFormatString(): String {
            val formato = DecimalFormat("#,##0.00")
            return formato.format(this)

    }
}