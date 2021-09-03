package com.redbubble.redbubblehomework.data.repository

import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.Result
import kotlinx.coroutines.flow.Flow

interface MainDataSource {
    suspend fun getHomeItems(): Flow<Result<HomeResponse>>
}