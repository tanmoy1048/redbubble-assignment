package com.redbubble.redbubblehomework.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redbubble.redbubblehomework.data.model.ItemDetailResponse
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.repository.MainDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailFragmentViewModel @Inject constructor(
    private val mainRepository: MainDataSource
) : ViewModel() {
    private val _detailResponse = MutableLiveData<Result<ItemDetailResponse>>()
    private val id = MutableLiveData<String>()

    val workDetail = Transformations.map(_detailResponse) { result ->
        if (result is Result.Success) {
            result.data?.workDetails
        } else
            null
    }

    val error = Transformations.map(_detailResponse) { result ->
        if (result is Result.Failure) {
            result.message
        } else
            null
    }

    val isLoading = Transformations.map(_detailResponse) {
        it is Result.Loading
    }

    fun setId(id: String) {
        this.id.value = id
        fetchItemDetail()
    }

    fun fetchItemDetail() {
        viewModelScope.launch {
            id.value?.let {
                mainRepository.getItemDetail(it).collect { result ->
                    _detailResponse.value = result
                }
            }
        }
    }
}