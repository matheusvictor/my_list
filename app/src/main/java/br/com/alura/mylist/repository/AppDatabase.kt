package br.com.alura.mylist.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.mylist.model.Product
import br.com.alura.mylist.repository.dao.ProductDAO
import br.com.alura.mylist.repository.converter.Converters

@Database(entities = [Product::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "my_list.db"
            ).allowMainThreadQueries()
                .build()
        }
    }

}
