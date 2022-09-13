package com.example.firstapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.firstapp.databinding.ImageTitleDescButtonComponentBinding
import com.example.firstapp.databinding.RepoDetailViewComponentBinding
import com.example.firstapp.ui.extensions.ImageTitleDescButtonListener
import com.example.firstapp.ui.extensions.setTextOrHide
import com.squareup.picasso.Picasso

class RepoDetailView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: RepoDetailViewComponentBinding
    init {
        binding = RepoDetailViewComponentBinding.inflate(LayoutInflater.from(context), this)
    }

    fun render(state: RepoDetailViewState){
        with(binding) {
            title.setTextOrHide(state.title)
            description.setTextOrHide(state.description)
            Picasso.get()
                .load(state.imageId.toString())
                .into(binding.repoPicture)
        }
    }

}

class RepoDetailViewState(
    val imageId: CharSequence,
    val title: CharSequence,
    val description: CharSequence,
)