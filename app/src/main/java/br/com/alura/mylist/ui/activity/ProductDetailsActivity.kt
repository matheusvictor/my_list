package br.com.alura.mylist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.mylist.databinding.ActivityProductDetailsBinding
import br.com.alura.mylist.extension.formatToRealCurrency
import br.com.alura.mylist.extension.tryLoadImage
import br.com.alura.mylist.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let {
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