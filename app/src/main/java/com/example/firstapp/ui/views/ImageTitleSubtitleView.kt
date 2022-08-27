package com.example.firstapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.firstapp.databinding.ImageTitleSubtitleViewBinding
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
            itemTitle.text = state.title
            itemDesc.text = state.subtitle

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