package br.com.alura.mylist.dao

import br.com.alura.mylist.model.Product

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll(): List<Product> {
        return products.toList() // cópia da lista, ao invés de passar referência
    }

    companion object {
        private val products = mutableListOf<Product>()
    }

}