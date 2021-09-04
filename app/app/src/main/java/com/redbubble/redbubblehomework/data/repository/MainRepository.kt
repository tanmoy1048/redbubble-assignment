package com.redbubble.redbubblehomework.data.repository

import com.redbubble.redbubblehomework.data.model.HomeResponse
import com.redbubble.redbubblehomework.data.model.ItemDetailResponse
import com.redbubble.redbubblehomework.data.model.Result
import com.redbubble.redbubblehomework.data.remote.NetworkServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(val networkServiceApi: NetworkServiceApi) :
    MainDataSource {
    companion object {
        const val TAG = "MainRepository"
        const val DEFAULT_ERROR_MESSAGE = "Something is Wrong, Please try later"
    }

    override suspend fun getHomeItems(): Flow<Result<HomeResponse>> {
        return flow {
            emit(Result.Loading())
            val result = getResponse(
                request = { networkServiceApi.getHomeItems() })
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getItemDetail(id: String): Flow<Result<ItemDetailResponse>> {
        return flow {
            emit(Result.Loading())
            val result = getResponse(
                request = { networkServiceApi.getItemDetail(id) })
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.Success(result.body())
            } else {
                //Need to parse the error from Error Body
                Result.Failure(DEFAULT_ERROR_MESSAGE)
            }
        } catch (e: Throwable) {
            Result.Failure(e.localizedMessage ?: DEFAULT_ERROR_MESSAGE)
        }
    }
}