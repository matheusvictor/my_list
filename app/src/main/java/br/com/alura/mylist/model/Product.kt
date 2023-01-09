package br.com.alura.mylist.model

import java.math.BigDecimal
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val url: String? = null
) : Parcelable
