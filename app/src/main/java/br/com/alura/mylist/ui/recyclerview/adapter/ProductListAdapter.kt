package br.com.alura.mylist.ui.recyclerview.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.alura.mylist.model.Product
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.R

class ProductListAdapter(
    private val context: Context,
    var products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val dataSet = products.toMutableList() // cópia da lista recebida como parâmetro

    inner class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun linkProductToView(product: Product) {
            val productName = itemView.findViewById<TextView>(R.id.product_name)
            productName.text = product.productName
            productName.setTextColor(Color.BLACK)
            val productDescription = itemView.findViewById<TextView>(R.id.product_description)
            productDescription.text = product.description
            val productPrice = itemView.findViewById<TextView>(R.id.product_price)
            productPrice.text = product.price.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        // Transforma o layout em uma View
        val inflateView = LayoutInflater.from(context)
            .inflate(R.layout.product_item, parent, false)
        return ProductListViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = products[position]
        holder.linkProductToView(product)
    }

    override fun getItemCount(): Int = products.size

    fun update(products: List<Product>) {
        this.dataSet.clear()
        this.dataSet.addAll(products)
        notifyDataSetChanged() // notifica o adapter que os dados internos foram modificados
    }

}
