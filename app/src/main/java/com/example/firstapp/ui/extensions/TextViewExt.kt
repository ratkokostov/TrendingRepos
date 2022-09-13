package com.example.firstapp.ui.extensions

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextOrHide(title: CharSequence?){
    isVisible = !title.isNullOrEmpty()
    text = title
}

fun String.firstLetterUppercase() = this.replaceFirstChar { it.uppercase() }
