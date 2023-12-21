package com.scafisystems.myecommerce.util

import java.text.DecimalFormat

object Extensions {

    fun Double.toFormatString(): String {
        val formato = DecimalFormat("#,##0.00")
        return formato.format(this)

    }

    fun String.isInt(): Boolean {
        return try {
            this.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun String.isDouble(): Boolean {
        return try {
            this.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun String.removeExcessDecimal(): String {
        val parts = this.split(".")
        return when {
            parts.size == 1 -> this // Sem ponto decimal
            parts[1].length <= 2 -> this // Menos ou igual a 2 casas decimais
            else -> "${parts[0]}.${parts[1].substring(0, 2)}"
        }
    }
}