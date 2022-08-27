package com.example.firstapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.firstapp.databinding.ImageTitleDescButtonComponentBinding
import com.squareup.picasso.Picasso

class ImageTitleDescButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ImageTitleDescButtonComponentBinding

    init {
        binding = ImageTitleDescButtonComponentBinding.inflate(LayoutInflater.from(context), this)
    }

    fun render(imageId: Int) {
        with(binding) {
            Picasso.get()
                .load(imageId)
                .into(binding.noInternetImage)
        }
    }

}
