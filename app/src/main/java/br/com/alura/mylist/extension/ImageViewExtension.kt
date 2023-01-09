package br.com.alura.mylist.extension

import coil.load
import br.com.alura.mylist.R
import android.widget.ImageView

fun ImageView.tryLoadImage(
    url: String? = null,
    fallback: Int = R.drawable.default_image
) {
    load(url) {
        fallback(fallback)
        error(R.drawable.default_image)
        placeholder(R.drawable.default_image)
    }
}