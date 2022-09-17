package com.example.firstapp.domain.use_case

import com.example.firstapp.model.GithubTrending
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.util.Resource
import java.io.IOException
import javax.inject.Inject

class RefreshAndroidReposUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke() : Resource<out GithubTrending?> {
        return try{
            repository.getApiDataForAndroidRepos()
        } catch(e: IOException){
            Resource.Error()
        }
    }

}