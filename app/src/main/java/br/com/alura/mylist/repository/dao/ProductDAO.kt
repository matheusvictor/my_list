package br.com.alura.mylist.repository.dao

import androidx.room.*
import br.com.alura.mylist.model.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM Product")
    fun findAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg product: Product)

    @Delete
    fun delete(vararg product: Product)

    @Update
    fun update(vararg product: Product)

}