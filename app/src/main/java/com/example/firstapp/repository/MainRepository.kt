package com.example.firstapp.repository

import androidx.lifecycle.LiveData
import com.example.firstapp.model.Item
import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun doNetworkCall(): Resource
    fun getAllRepos(): List<Item>?
    suspend fun insertRepo(repo: Item)
    suspend fun deleteRepo(repo: Item)
    suspend fun updateRepo(repo: Item)
}