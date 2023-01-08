package br.com.alura.mylist.model

import java.math.BigDecimal

data class Product(
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val url: String? = null
)
