package com.example.firstapp.repository

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.util.Resource

interface MainRepository {
    suspend fun getApiDataForAndroidRepos(): Resource<out GithubTrending?>
    suspend fun getApiDataForReadmeContent(full_name: String?, default_branch: String?, id : Int?): Resource<out String?>
    suspend fun getDataForAndroidFromCacheOrApi(): Resource<out GithubTrending?>
    suspend fun getDataForReadmeContentFromCacheOrApi(full_name: String?, default_branch: String?, id : Int?): Resource<out String?>
}