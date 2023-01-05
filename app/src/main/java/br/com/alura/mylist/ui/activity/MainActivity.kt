package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.R
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : Activity() {

    private val productsDAO = ProductsDAO()
    private val adapter = ProductListAdapter(
        context = this,
        products = productsDAO.findAll()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        setFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(productsDAO.findAll())
    }

    private fun setRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_product_list)
        recyclerView.adapter = adapter
    }

    private fun setFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floating_add_item)
        fab.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intentFormActivity = Intent(this, ProductFormActivity::class.java)
        startActivity(intentFormActivity)
    }

}