package com.example.firstapp.network

import javax.inject.Inject

class GithubBaseUrlProviderImpl @Inject constructor() : GithubBaseUrlProvider {

    override fun getUrl(baseUrl: String): String{
        return baseUrl
    }
}