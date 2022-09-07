package com.example.firstapp.repository

import androidx.lifecycle.LiveData
import com.example.firstapp.model.Item
import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun doNetworkCall(): Resource
}