package com.redbubble.redbubblehomework.ui.detail.work

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.redbubble.redbubblehomework.data.model.ItemDetailResponse
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.repository.MainDataSource
import com.redbubble.redbubblehomework.ui.detail.DetailFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailFragmentViewModel @Inject constructor(
    private val mainRepository: MainDataSource
) : DetailFragmentViewModel() {
    private val id = MutableLiveData<String>()

    val workDetail = Transformations.map(_detailResponse) { result ->
        if (result is Result.Success) {
            result.data?.workDetails
        } else
            null
    }

    fun setId(id: String) {
        this.id.value = id
        fetchItemDetail()
    }

    override fun fetchItemDetail() {
        viewModelScope.launch {
            id.value?.let {
                mainRepository.getItemDetail(it).collect { result ->
                    _detailResponse.value = result
                }
            }
        }
    }
}