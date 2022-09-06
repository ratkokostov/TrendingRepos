package com.example.firstapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.firstapp.databinding.ImageTitleDescButtonComponentBinding
import com.example.firstapp.ui.extensions.ImageTitleDescButtonListener
import com.example.firstapp.ui.extensions.setTextOrHide
import com.squareup.picasso.Picasso

class ImageTitleDescButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ImageTitleDescButtonComponentBinding
    var listener: ImageTitleDescButtonListener? = null
    init {
        binding = ImageTitleDescButtonComponentBinding.inflate(LayoutInflater.from(context), this).apply {
            button.setOnClickListener { listener?.onButtonClick() }
        }
    }

    fun render(state: ImageTitleDescriptionButtonViewState){
        with(binding) {
            title.setTextOrHide(state.title)
            description.setTextOrHide(state.description)
            button.setTextOrHide(state.buttonText)
            Picasso.get()
                .load(state.imageId)
                .into(binding.noInternetImage)
        }
    }

}

class ImageTitleDescriptionButtonViewState(
    val imageId: Int,
    val title: CharSequence,
    val description: CharSequence,
    val buttonText: String?,
)

