package com.example.firstapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.ApiInstance
import com.example.firstapp.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

      lateinit var recyclerListLiveData : MutableLiveData<GithubTrending>
      init{
          recyclerListLiveData = MutableLiveData()
      }


    fun getRecyclerListObserver(): MutableLiveData<GithubTrending>{
        return recyclerListLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO){
          val retrofitInstance = ApiInstance.getRetroInstance().create(ApiService::class.java)
          val response =  retrofitInstance.getDataFromApi("android")
            recyclerListLiveData.postValue(response)

        }
    }
}