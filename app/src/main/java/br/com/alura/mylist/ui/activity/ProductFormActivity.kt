package br.com.alura.mylist.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.mylist.R
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.databinding.ActivityProductFormBinding
import br.com.alura.mylist.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private val binding by lazy {
        ActivityProductFormBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setConfirmButton()
    }

    private fun setConfirmButton() {

        val confirmButton = binding.btFormConfirm
        val productsDAO = ProductsDAO()

        confirmButton.setOnClickListener {
            val newProduct = createNewProduct()

            productsDAO.add(newProduct)
            Log.i("ProductFormActivity", "onCreate: ${productsDAO.findAll()}")
            finish() // finaliza activity e remove da pilha
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
            price = productPrice
        )
    }

}