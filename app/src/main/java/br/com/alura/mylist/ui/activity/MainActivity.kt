package br.com.alura.mylist.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.databinding.ActivityMainBinding
import br.com.alura.mylist.repository.AppDatabase
import br.com.alura.mylist.ui.recyclerview.adapter.ProductListAdapter

class MainActivity : Activity() {

    private val productsDAO = ProductsDAO()
    private val bindingActivityMain by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy {
        ProductListAdapter(
            context = this,
            products = productsDAO.findAll()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingActivityMain.root) // vínculo da View com a Activity
        setRecyclerView()
        setFab()
    }

    override fun onResume() {
        super.onResume()

        // AppDatabase Instance
        val db = AppDatabase.getInstance(this)

        val productsDAO = db.productDao()
        adapter.update(productsDAO.findAll())
    }

    private fun setRecyclerView() {
        val recyclerView = bindingActivityMain.rvProductList
        recyclerView.adapter = adapter
        adapter.whenClickOnItem = {
            val intent = Intent(
                this,
                ProductDetailsActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }

    private fun setFab() {
        val fab = bindingActivityMain.extendedFab
        fab.setOnClickListener {
            goToProductForm()
        }
    }

    private fun goToProductForm() {
        val intentFormActivity = Intent(this, ProductFormActivity::class.java)
        startActivity(intentFormActivity)
    }

}