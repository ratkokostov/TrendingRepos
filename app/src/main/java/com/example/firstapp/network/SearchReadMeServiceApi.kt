package com.example.firstapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchReadMeServiceApi {
    @GET("/{full_name}/README.md")
    suspend fun getDataForReadme(@Path("full_name") full_name : String) : Response<String?>

}