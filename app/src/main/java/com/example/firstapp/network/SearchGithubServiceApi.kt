package com.example.firstapp.network

import com.example.firstapp.model.GithubTrending
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchGithubServiceApi {
    @GET("search/repositories")
    suspend fun getDataFromApi(@Query("q") query: String): Response<GithubTrending>

}
