package br.com.alura.mylist.extension

import coil.load
import br.com.alura.mylist.R
import android.widget.ImageView

fun ImageView.tryLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.default_image)
        error(R.drawable.default_image)
        placeholder(R.drawable.default_image)
    }
}