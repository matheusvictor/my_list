package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.R
import br.com.alura.mylist.model.Product
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        clickOnFloatingButton()
    }

    private fun clickOnFloatingButton() {
        val fab = findViewById<FloatingActionButton>(R.id.floating_add_item)
        fab.setOnClickListener {
            val intentFormActivity = Intent(this, ProductFormActivity::class.java)
            startActivity(intentFormActivity)
        }
    }
}