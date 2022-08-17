package com.example.firstapp.repository

import com.example.firstapp.domain.MainRepository
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.ApiService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: ApiService
) : MainRepository {
    override suspend fun doNetworkCall(): GithubTrending {
        return api.getDataFromApi("android")
    }
}