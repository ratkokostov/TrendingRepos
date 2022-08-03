package com.example.firstapp.network

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("search/repositories?q=android")
    suspend fun getPosts(): GithubTrending
    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)

            }
            return apiService!!
        }
    }
}