package br.com.alura.mylist.dao

import br.com.alura.mylist.model.Product
import java.math.BigDecimal

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll(): List<Product> {
        return products.toList() // cópia da lista, ao invés de passar referência
    }

    companion object {
        private val products: MutableList<Product> = mutableListOf(
            Product(
                productName = "Example",
                description = "This a simple description",
                price = BigDecimal("1.99")
            )
        )
    }
}