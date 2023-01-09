package br.com.alura.mylist.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.mylist.R
import br.com.alura.mylist.databinding.ActivityProductFormBinding
import br.com.alura.mylist.extension.tryLoadImage
import br.com.alura.mylist.model.Product
import br.com.alura.mylist.repository.AppDatabase
import br.com.alura.mylist.ui.dialog.FormImageDialog
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }
    private var urlImage: String? = null
    private var productId: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        title = "Add New Product" // set title for activity

        setConfirmButton()

        binding.formImageProduct.setOnClickListener {
            FormImageDialog(this)
                .show(urlImage) {
                    urlImage = it
                    binding.formImageProduct.tryLoadImage(urlImage)
                }
        }

        intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let { product ->
            title =
                "Edit Produtc" // altera o título da activity se for aberta a partir do menu de edição
            productId = product.id
            urlImage = product.url
            binding.formImageProduct.tryLoadImage(product.url)
            binding.formProductName.setText(product.productName)
            binding.formProductDescription.setText(product.description)
            binding.formProductPrice.setText(product.price.toPlainString())
        }

    }

    private fun setConfirmButton() {

        val confirmButton = binding.btFormConfirm

        // AppDatabase Instance
        val db = AppDatabase.getInstance(this)

        val productsDAO = db.productDao()

        confirmButton.setOnClickListener {
            val newProduct = createNewProduct()

            if (productId > 0L) {
                productsDAO.update(newProduct)
            } else {
                productsDAO.save(newProduct)
            }

            productsDAO.save(newProduct)
            finish()
        }
    }

    private fun createNewProduct(): Product {
        val fieldProductName = binding.formProductName
        val productName = fieldProductName.text.toString()
        Log.i("ProductFormActivity", "onCreate: $productName")

        val fieldProductDescription = binding.formProductDescription
        val productDescription = fieldProductDescription.text.toString()
        Log.i("ProductFormActivity", "onCreate: $productDescription")

        val fieldProductPrice = binding.formProductPrice
        val productPriceString = fieldProductPrice.text.toString()

        val productPrice = if (productPriceString.isNotBlank()) {
            BigDecimal(productPriceString)
        } else {
            BigDecimal.ZERO
        }

        return Product(
            id = productId,
            productName = productName,
            description = productDescription,
            price = productPrice,
            url = urlImage
        )
    }

}