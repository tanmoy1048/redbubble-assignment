package com.redbubble.redbubblehomework.data.repository

import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.ItemDetailResponse
import com.redbubble.redbubblehomework.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MainDataSource {
    suspend fun getHomeItems(): Flow<Result<HomeResponse>>

    suspend fun getItemDetail(id: String): Flow<Result<ItemDetailResponse>>
}