package com.example.firstapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.example.firstapp.databinding.ImageTitleSubtitleViewBinding
import com.example.firstapp.ui.extensions.setTextOrHide
import com.squareup.picasso.Picasso


class ImageTitleSubtitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: ImageTitleSubtitleViewBinding

    init {
        binding = ImageTitleSubtitleViewBinding.inflate(LayoutInflater.from(context), this)
    }

    fun render(state: ImageTitleSubtitleViewState) {
        with(binding) {
            itemTitle.setTextOrHide(state.title)
            itemDesc.setTextOrHide(state.subtitle)
            Picasso.get()
                .load(state.imageUrl)
                .into(itemImage)
        }
    }

}

class ImageTitleSubtitleViewState(
    val title: String?,
    val subtitle: String?,
    val imageUrl: String?
)