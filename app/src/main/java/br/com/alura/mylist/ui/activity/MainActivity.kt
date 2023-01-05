package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.databinding.ActivityMainBinding
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter

class MainActivity : Activity() {

    private val productsDAO = ProductsDAO()
    private val bindingActivityMain by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivityMain.root) // vínculo da View com a Activity
        setRecyclerView()
        setFab()
    }

    override fun onResume() {
        super.onResume()
//        adapter.update(productsDAO.findAll())
        setContentView(bindingActivityMain.root)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val recyclerView = bindingActivityMain.rvProductList
        recyclerView.adapter = ProductListAdapter(
            context = this,
            products = productsDAO.findAll()
        )
    }

    private fun setFab() {
        val fab = bindingActivityMain.floatingAddItem
        fab.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intentFormActivity = Intent(this, ProductFormActivity::class.java)
        startActivity(intentFormActivity)
    }

}