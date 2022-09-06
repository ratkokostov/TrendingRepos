package com.example.firstapp.util

import com.example.firstapp.model.GithubTrending

sealed class Resource(
    val data: GithubTrending? = null,
) {
    class Success(data: GithubTrending) : Resource(data)
    class Error() : Resource()
    class Loading : Resource()
}
