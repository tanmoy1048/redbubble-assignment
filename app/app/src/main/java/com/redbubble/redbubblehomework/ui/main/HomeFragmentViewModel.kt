package com.redbubble.redbubblehomework.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.repository.MainDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val mainRepository: MainDataSource
) : ViewModel() {
    private val _homeResponse = MutableLiveData<Result<HomeResponse>>()

    val homeItems = Transformations.map(_homeResponse) { result ->
        (result as? Result.Success)?.data?.home
    }

    val error = Transformations.map(_homeResponse) { result ->
        (result as? Result.Failure)?.message
    }

    val isLoading = Transformations.map(_homeResponse) {
        it is Result.Loading
    }

    fun fetchHomeItems() {
        viewModelScope.launch {
            mainRepository.getHomeItems().collect {
                _homeResponse.value = it
            }
        }
    }
}