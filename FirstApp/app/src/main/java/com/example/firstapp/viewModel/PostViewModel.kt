package com.example.firstapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.model.Item
import com.example.firstapp.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    val postListResponse: MutableStateFlow<List<Item>> = MutableStateFlow(listOf<Item>())
    var errorMessage: String by mutableStateOf("ErrorMessage")

    fun getPostsList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val repoList = apiService.getPosts()
                postListResponse.emit(repoList.items)
                Log.d("Lista", repoList.toString())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.e("PostViewModel", " ", e)
            }
        }
    }
}