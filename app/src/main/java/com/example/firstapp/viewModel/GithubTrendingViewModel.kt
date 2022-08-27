package com.example.firstapp.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_ETHERNET
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities.*
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import androidx.lifecycle.*
import com.example.firstapp.MyApp
import com.example.firstapp.domain.MainRepository
import com.example.firstapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class GithubTrendingViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel(){

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

