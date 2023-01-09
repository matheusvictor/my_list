package br.com.alura.mylist.model

import java.math.BigDecimal
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: BigDecimal,
    @ColumnInfo(name = "url") val url: String? = null
) : Parcelable
