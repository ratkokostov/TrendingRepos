package com.example.firstapp.util


sealed class Resource<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error : Resource<Nothing>()
    class Loading : Resource<Nothing>()
}
