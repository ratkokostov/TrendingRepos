package com.example.firstapp.model

import android.content.ClipData

data class Post(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: MutableList<Item>
)


