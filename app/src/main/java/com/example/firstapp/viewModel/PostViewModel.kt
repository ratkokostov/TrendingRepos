package com.example.firstapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.network.ApiInstance
import com.example.firstapp.network.ApiService
import com.example.firstapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _recyclerListLiveData: MutableLiveData<GithubTrending> by lazy {MutableLiveData()}
    val recyclerListLiveData: LiveData<GithubTrending>
        get() = _recyclerListLiveData

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            _recyclerListLiveData.postValue(MainRepository.getInstance().fetchOnlineData())

        }
    }
}