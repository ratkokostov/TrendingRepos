package com.example.firstapp.repository

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun doNetworkCallForRepos(): Resource<out GithubTrending?>
    suspend fun doNetworkCallForReadme(full_name: String?,default_branch: String?): Resource<out String?>

}