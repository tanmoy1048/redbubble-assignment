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

abstract class DetailFragmentViewModel : ViewModel() {
    protected val _detailResponse = MutableLiveData<Result<ItemDetailResponse>>()

    val error = Transformations.map(_detailResponse) { result ->
        if (result is Result.Failure) {
            result.message
        } else
            null
    }

    val isLoading = Transformations.map(_detailResponse) {
        it is Result.Loading
    }

    abstract fun fetchItemDetail()
}