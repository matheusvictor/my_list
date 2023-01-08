package br.com.alura.mylist.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.mylist.databinding.FormImageBinding
import br.com.alura.mylist.extension.tryLoadImage

class FormImageDialog(private val context: Context) {

    fun show(
        urlDefault: String? = null,
        whenLoadedImage: (url: String) -> Unit
    ) {

        FormImageBinding.inflate(LayoutInflater.from(context)).apply {
            urlDefault.let {
                ivFormImageSet.tryLoadImage(it)
                edtImageUrl.setText(it)
            }

            btFormLoadImage.setOnClickListener {
                val url = edtImageUrl.text.toString()
                ivFormImageSet.tryLoadImage(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setNegativeButton("Cancel") { _, _ -> }
                .setPositiveButton("Confirm") { _, _ ->
                    val url = edtImageUrl.text.toString()
                    whenLoadedImage(url)
                }
                .show()

        }

    }
}