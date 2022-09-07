package com.example.firstapp.dataLayer

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubTrendingReposDAO {

    @Insert
    suspend fun insertRepo(githubTrending: Item)

    @Update
    suspend fun updateRepo(githubTrending: Item)

    @Query("SELECT * FROM github_trending_repos")
    fun getAllRepos(): List<Item>

    @Delete
    suspend fun deleteRepo(githubTrending: Item)

    @Query("DELETE FROM github_trending_repos")
    fun deleteAllRepos()
}