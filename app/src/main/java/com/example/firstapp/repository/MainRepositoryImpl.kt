package com.example.firstapp.repository

import com.example.firstapp.domain.MainRepository
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.SearchGithubServiceApi
import com.example.firstapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: SearchGithubServiceApi
) : MainRepository {
    override suspend fun doNetworkCall(): Resource {
        return withContext(Dispatchers.IO){
            try{
                val response: Response<GithubTrending> = api.getDataFromApi("android")

                if(response.isSuccessful){

                    Resource.Success(data = response.body()!!)

                }
                else{
                    Resource.Error()
                }
            } catch (e: IOException){

                Resource.Error()
            }
        }
    }
}