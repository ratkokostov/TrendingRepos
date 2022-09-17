package com.example.firstapp.ui.fragments.detailviewfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.dataLayer.GithubTrendingReposDAO
import com.example.firstapp.domain.use_case.InitializeReadmeContentUseCase
import com.example.firstapp.domain.use_case.RefreshAndroidReposUseCase
import com.example.firstapp.domain.use_case.RefreshReadmeContentUseCase
import com.example.firstapp.repository.MainRepository
import com.example.firstapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val initializeReadmeContentUseCase: InitializeReadmeContentUseCase,
    private val refreshReadmeContentUseCase: RefreshReadmeContentUseCase
) : ViewModel() {

    private val _readmeData: MutableLiveData<Resource<out String?>> by lazy { MutableLiveData() }
    val readmeData: LiveData<Resource<out String?>>
        get() = _readmeData

    fun initializeReadmeContent(full_name: String?, default_branch: String?, id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _readmeData.postValue(
                initializeReadmeContentUseCase.invoke(
                    full_name,
                    default_branch,
                    id
                )
            )
        }
    }

    fun refreshApiCallForReadmeContent(full_name: String?, default_branch: String?, id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            _readmeData.postValue(refreshReadmeContentUseCase.invoke(full_name, default_branch, id))
        }
    }
}


