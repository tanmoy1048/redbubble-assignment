package com.redbubble.redbubblehomework.ui.main

import androidx.lifecycle.*
import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _homeResponse = MutableLiveData<Result<HomeResponse>>()
    val homeResponse: LiveData<Result<HomeResponse>> = _homeResponse

    val isLoading = Transformations.map(homeResponse) {
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