package com.example.firstapp.repository

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun doNetworkCallForRepos(): Resource<out GithubTrending?>
    suspend fun doNetworkCallForReadme(full_name: String?,default_branch: String?, id : Int?): Resource<out String?>
    suspend fun doDatabaseCallForRepos(): Resource<out GithubTrending?>
}