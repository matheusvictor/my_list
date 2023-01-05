package br.com.alura.mylist.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.mylist.R
import br.com.alura.mylist.dao.ProductsDAO
import br.com.alura.mylist.model.Product
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setConfirmButton()
    }

    private fun setConfirmButton() {

        val productsDAO = ProductsDAO()
        val confirmButton = findViewById<Button>(R.id.bt_form_confirm)

        confirmButton.setOnClickListener {
            val newProduct = createNewProduct()

            productsDAO.add(newProduct)
            Log.i("ProductFormActivity", "onCreate: ${productsDAO.findAll()}")
            finish() // finaliza activity e remove da pilha
        }
    }

    private fun createNewProduct(): Product {
        val fieldProductName = findViewById<EditText>(R.id.form_product_name)
        val productName = fieldProductName.text.toString()
        Log.i("ProductFormActivity", "onCreate: $productName")

        val fieldProductDescription = findViewById<EditText>(R.id.form_product_description)
        val productDescription = fieldProductDescription.text.toString()
        Log.i("ProductFormActivity", "onCreate: $productDescription")

        val fieldProductPrice = findViewById<EditText>(R.id.form_product_price)
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