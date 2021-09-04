package com.redbubble.redbubblehomework.data.repository

import com.redbubble.redbubblehomework.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeRepository : MainDataSource {
    val item = HomeItem(
        "rafi",
        "some_id",
        Price(10.50f, "AUD"),
        true,
        "",
        "awesome art",
        "WORK"
    )
    val homeResponse = HomeResponse(
        listOf(item)
    )


    override suspend fun getHomeItems(): Flow<Result<HomeResponse>> {
        return flow {
            emit(Result.Success(homeResponse))
        }.flowOn(Dispatchers.Main)
    }

    override suspend fun getItemDetail(id: String): Flow<Result<ItemDetailResponse>> {
//        return flow {
//            emit(Result.Success())
//        }.flowOn(Dispatchers.Main)
    }
}