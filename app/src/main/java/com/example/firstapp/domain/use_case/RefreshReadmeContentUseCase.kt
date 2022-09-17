package com.example.firstapp.domain.use_case

import android.util.Log
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.util.Resource
import java.io.IOException
import javax.inject.Inject

class RefreshReadmeContentUseCase @Inject constructor(
    private val repository: MainRepository,
) {
    suspend operator fun invoke(full_name: String?, default_branch: String?, id: Int?) : Resource<out String?> {
        return try{
            repository.getApiDataForReadmeContent(full_name,default_branch,id)
        } catch(e: IOException){
            Resource.Error()
        }
    }

}