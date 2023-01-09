package br.com.alura.mylist.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.alura.mylist.model.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>

    @Insert
    fun save(vararg product: Product)

    @Delete
    fun delete(vararg product: Product)

    @Update
    fun update(vararg product: Product)

}