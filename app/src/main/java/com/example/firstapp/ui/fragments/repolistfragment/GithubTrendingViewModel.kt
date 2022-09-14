package com.example.firstapp.ui.fragments.repolistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.model.GithubTrending
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubTrendingViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _recyclerListLiveData: MutableLiveData<Resource<out GithubTrending?>> by lazy { MutableLiveData() }
    val recyclerListLiveData: LiveData<Resource<out GithubTrending?>>
        get() = _recyclerListLiveData
    fun makeApiCallForRepos() {
        viewModelScope.launch(Dispatchers.IO) {
            _recyclerListLiveData.postValue(repository.doNetworkCallForRepos())
        }
    }
    fun fetchFromDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            _recyclerListLiveData.postValue(repository.doDatabaseCallForRepos())
        }
    }
}


