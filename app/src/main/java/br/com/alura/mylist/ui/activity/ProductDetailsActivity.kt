package br.com.alura.mylist.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.mylist.R
import br.com.alura.mylist.databinding.ActivityProductDetailsBinding
import br.com.alura.mylist.extension.formatToRealCurrency
import br.com.alura.mylist.extension.tryLoadImage
import br.com.alura.mylist.model.Product
import br.com.alura.mylist.repository.AppDatabase

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (::product.isInitialized) {
            val db = AppDatabase.getInstance(this)
            val productDao = db.productDao()

            when (item.itemId) {
                R.id.menu_details_edit_product -> {

                }
                R.id.menu_details_delete_product -> {
                    productDao.delete(product)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun tryLoadProduct() {
        intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let {
            this.product = it
            fillFields(it)
        } ?: finish()
    }

    private fun fillFields(product: Product) {
        with(binding) {
            productDetailsImage.tryLoadImage(product.url)
            productNameDetails.text = product.productName
            productDescriptionDetails.text = product.description
            productPriceDetails.text = product.price.formatToRealCurrency()
        }
    }

}