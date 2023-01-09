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
    }

    private fun setConfirmButton() {

        val confirmButton = binding.btFormConfirm

        // AppDatabase Instance
        val db = AppDatabase.getInstance(this)

        val productsDAO = db.productDao()

        confirmButton.setOnClickListener {
            val newProduct = createNewProduct()
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
            productName = productName,
            description = productDescription,
            price = productPrice,
            url = urlImage
        )
    }

}