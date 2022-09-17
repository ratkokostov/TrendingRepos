package com.example.firstapp.repository

import android.util.Log
import com.example.firstapp.dataLayer.GithubTrendingReposDAO
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.SearchGithubServiceApi
import com.example.firstapp.network.SearchReadMeServiceApi
import com.example.firstapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val searchGithubServiceApi: SearchGithubServiceApi,
    private val searchReadMeServiceApi: SearchReadMeServiceApi,
    private val appDao: GithubTrendingReposDAO
) : MainRepository {
    override suspend fun getApiDataForAndroidRepos(): Resource<out GithubTrending?> {
            val response: Response<GithubTrending> =
                searchGithubServiceApi.doApiCallForAndroidRepos("android")
        return if (response.isSuccessful) {
                appDao.deleteAllRepos()
                response.body()?.items?.forEach {
                    appDao.insertRepo(it)
                }
                Resource.Success(data = GithubTrending(response.body()!!.items.sortedBy { it.id }))
            } else {
                Resource.Error()
            }
    }

    override suspend fun getDataForAndroidFromCacheOrApi(): Resource<out GithubTrending?> {
        return if (appDao.getAllRepos().isNotEmpty()) {
            Resource.Success(data = GithubTrending(appDao.getAllRepos()))
        } else {
            getApiDataForAndroidRepos()
        }
    }

    override suspend fun getApiDataForReadmeContent(
        full_name: String?,
        default_branch: String?,
        id: Int?
    ): Resource<out String?> {
            val response: Response<String?> =
                searchReadMeServiceApi.getDataForReadme("$full_name/$default_branch")
            return if (response.isSuccessful) {
                appDao.updateReadme(response.body(), id)
                Resource.Success(data = response.body())


            } else {
                Resource.Success(data = appDao.getReadmeContent(id))
            }
    }
    override suspend fun getDataForReadmeContentFromCacheOrApi(
        full_name: String?,
        default_branch: String?,
        id: Int?
    ): Resource<out String?> {
        return if (appDao.getReadmeContent(id)?.isNotBlank() == true) {
            Resource.Success(data = appDao.getReadmeContent(id))
        } else {
            getApiDataForReadmeContent(full_name,default_branch,id)
        }
    }

}

