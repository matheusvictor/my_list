package br.com.alura.mylist.ui.activity

import android.content.Intent
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

    private var productId: Long? = null
    private var product: Product? = null

    private val productDao by lazy {
        AppDatabase.getInstance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    override fun onResume() {
        super.onResume()
        productId?.let { id ->
            product = productDao.findById(id)
        }
        product?.let {
            fillFields(it)
        } ?: finish() // fecha a tela se o produto procurado no BD for nulo
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_details_edit_product -> {
                Intent(this, ProductFormActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO, product)
                    startActivity(this)
                }
            }
            R.id.menu_details_delete_product -> {
                product?.let {
                    productDao.delete(it)
                }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryLoadProduct() {
        intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let {
            productId = it.id
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