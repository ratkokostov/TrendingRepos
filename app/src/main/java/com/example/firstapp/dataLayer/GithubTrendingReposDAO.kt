package com.example.firstapp.dataLayer

import androidx.room.*
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.util.Resource
import retrofit2.Response

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

    @Query("UPDATE github_trending_repos SET readmeContent =:readmeContent WHERE id =:id")
    fun updateReadme(readmeContent: String?, id: Int?)

    @Query("SELECT readmeContent FROM github_trending_repos WHERE id =:id")
    fun getReadmeContent(id: Int?): String?

}