package com.example.firstapp.ui.fragments.detailviewfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    private val _readmeData: MutableLiveData<Resource<String?>> by lazy { MutableLiveData() }
    val readmeData: LiveData<Resource<String?>>
        get() = _readmeData

    fun makeApiCallForReadme(full_name: String?, default_branch: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            _readmeData.postValue(Resource.Loading())
            _readmeData.postValue(repository.doNetworkCallForReadme(full_name, default_branch))
        }
    }

}
