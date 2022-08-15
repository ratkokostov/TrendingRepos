package com.example.firstapp.repository

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.ApiInstance
import com.example.firstapp.network.ApiService

class MainRepository{
    suspend fun fetchOnlineData(): GithubTrending {
        val retrofitInstance = ApiInstance.getRetroInstance().create(ApiService::class.java)
        return retrofitInstance.getDataFromApi("android")
    }
    companion object{
        private lateinit var me: MainRepository

        @JvmStatic
        fun getInstance() : MainRepository{
            if(!this::me.isInitialized) {
                me = MainRepository()
            }

            return me
        }
    }

}