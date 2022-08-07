package com.example.firstapp.ui

import com.example.firstapp.model.Item

interface PostClickHandler {
    fun clickedPostItem(item: Item)
}