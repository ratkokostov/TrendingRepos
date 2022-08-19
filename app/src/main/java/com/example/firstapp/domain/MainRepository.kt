package com.example.firstapp.domain

import com.example.firstapp.model.GithubTrending

interface MainRepository {
    suspend fun doNetworkCall(): GithubTrending
}