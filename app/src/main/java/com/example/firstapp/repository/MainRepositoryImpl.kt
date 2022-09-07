package com.example.firstapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.firstapp.dataLayer.GithubTrendingReposDAO
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.model.Item
import com.example.firstapp.network.SearchGithubServiceApi
import com.example.firstapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: SearchGithubServiceApi,
    private val appDao: GithubTrendingReposDAO
) : MainRepository {

    override suspend fun doNetworkCall(): Resource {
        return withContext(Dispatchers.IO){
            try{
                val response: Response<GithubTrending> = api.getDataFromApi("android")


                if(response.isSuccessful){
                    appDao.deleteAllRepos()
                    response.body()?.items?.forEach {
                        appDao.insertRepo(it)
                    }
                    Resource.Success(data = GithubTrending(response.body()!!.items.sortedBy{ it.id}))

                }
                else{
                    Resource.Error()
                }
            } catch (e: IOException){
                if(appDao.getAllRepos().isNotEmpty()){
                    Resource.Success(data = GithubTrending(appDao.getAllRepos()))
                } else {
                    Resource.Error()
                }
            }
        }
    }
}