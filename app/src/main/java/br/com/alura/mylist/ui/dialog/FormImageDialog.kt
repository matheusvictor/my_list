package br.com.alura.mylist.ui.dialog

import android.util.Log
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.mylist.extension.tryLoadImage
import br.com.alura.mylist.databinding.FormImageBinding

class FormImageDialog(private val context: Context) {

    fun show(whenLoadedImage: (url: String) -> Unit) {
        val binding = FormImageBinding.inflate(LayoutInflater.from(context))

        binding.btFormLoadImage.setOnClickListener {

            val url = binding.edtImageUrl.text.toString()
            binding.ivFormImageSet.tryLoadImage(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setNegativeButton("Cancel") { _, _ -> }
            .setPositiveButton("Confirm") { _, _ ->
                val url = binding.edtImageUrl.text.toString()
                Log.i("FormImageDialog", "show: $url")
                whenLoadedImage(url)
            }
            .show()

    }
}