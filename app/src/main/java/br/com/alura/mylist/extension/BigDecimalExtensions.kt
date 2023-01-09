package br.com.alura.mylist.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.formatToRealCurrency(): String {
    val formatter: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatter.format(this)
}