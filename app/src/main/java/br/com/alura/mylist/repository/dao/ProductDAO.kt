package br.com.alura.mylist.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.mylist.model.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>

    @Insert
    fun save(vararg product: Product)

}