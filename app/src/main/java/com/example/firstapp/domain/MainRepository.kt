package com.example.firstapp.domain

import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun doNetworkCall(): Resource
}