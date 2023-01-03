package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.R
import br.com.alura.mylist.model.Product
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_product_list)
        recyclerView.adapter = ProductListAdapter(
            context = this,
            products = listOf(
                Product("Example Name", "Example Desc", BigDecimal("1.99")),
                Product("Example Name", "Example Desc", BigDecimal("1.99")),
                Product("Example Name", "Example Desc", BigDecimal("1.99")),
            )
        )
    }
}