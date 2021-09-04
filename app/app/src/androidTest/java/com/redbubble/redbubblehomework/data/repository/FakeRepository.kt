package com.redbubble.redbubblehomework.data.repository

import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository() : MainDataSource {
    val homeResponse = HomeResponse(listOf())

    override suspend fun getHomeItems(): Flow<Result<HomeResponse>> {
        return flow {
            emit(Result.Loading())
            emit(Result.Success(homeResponse))
        }
    }
}