package br.com.alura.mylist.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.mylist.databinding.ProductItemBinding
import br.com.alura.mylist.extension.tryLoadImage
import br.com.alura.mylist.model.Product
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val dataSet = products.toMutableList() // cópia da lista recebida como parâmetro

    inner class ProductListViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun linkProductToView(product: Product) {
            val productName = binding.productName
            productName.text = product.productName
            val productDescription = binding.productDescription
            productDescription.text = product.description
            val productPrice = binding.productPrice

            val currencyFormatter: NumberFormat =
                NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val priceToReal: String = currencyFormatter.format(product.price)
            productPrice.text = priceToReal

            if (product.url.isNullOrBlank()) {
                binding.ivProductImage.visibility = View.GONE
            } else {
                binding.ivProductImage.visibility = View.VISIBLE
                binding.ivProductImage.tryLoadImage(product.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val bindingViewHolder = ProductItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ProductListViewHolder(bindingViewHolder)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = dataSet[position]
        holder.linkProductToView(product)
    }

    override fun getItemCount(): Int = dataSet.size

    fun update(products: List<Product>) {
        this.dataSet.clear()
        this.dataSet.addAll(products)
        notifyDataSetChanged() // notifica o adapter que os dados internos foram modificados
    }

}
