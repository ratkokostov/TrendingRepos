package com.example.firstapp.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.model.Item
import com.example.firstapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubTrendingViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _recyclerListLiveData: MutableLiveData<Resource> by lazy { MutableLiveData() }
    val recyclerListLiveData: LiveData<Resource>
        get() = _recyclerListLiveData

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            _recyclerListLiveData.postValue(Resource.Loading())
            _recyclerListLiveData.postValue(repository.doNetworkCall())
        }
    }
}


