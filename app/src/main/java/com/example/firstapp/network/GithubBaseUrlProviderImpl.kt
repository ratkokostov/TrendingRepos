package com.example.firstapp.network

import com.example.firstapp.BuildConfig
import javax.inject.Inject

class GithubBaseUrlProviderImpl @Inject constructor(): GithubBaseUrlProvider {

    override fun getUrl(): String = if (BuildConfig.DEBUG) {
        "https://api.github.com/"
    } else {
        "https://api.github.com/"
    }
}