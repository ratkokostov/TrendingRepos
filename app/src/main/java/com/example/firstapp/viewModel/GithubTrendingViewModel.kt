package com.example.firstapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.domain.MainRepository
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

