package com.redbubble.redbubblehomework.ui.detail.product

import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.repository.MainDataSource
import com.redbubble.redbubblehomework.ui.detail.DetailFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailFragmentViewModel @Inject constructor(
    private val mainRepository: MainDataSource
) : DetailFragmentViewModel() {

    val workDetail = Transformations.map(_detailResponse) { result ->
        if (result is Result.Success) {
            result.data?.productDetails
        } else
            null
    }

    override fun fetchItemDetail() {
        viewModelScope.launch {
                mainRepository.getProductDetail().collect { result ->
                    _detailResponse.value = result
                }
            }
    }
}