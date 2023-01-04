package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.R
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : Activity() {

    override fun onResume() {
        super.onResume()
        setContentView(R.layout.activity_main)

        val products = ProductsDAO()
        Log.i("MainActivity", "onCreate: ${products.findAll()}")

        val recyclerView = findViewById<RecyclerView>(R.id.rv_product_list)
        recyclerView.adapter = ProductListAdapter(
            context = this,
            products = products.findAll()
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