package com.redbubble.redbubblehomework.data.remote

import com.redbubble.redbubblehomework.data.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkServiceApi {
    @GET("explore.json")
    suspend fun getHomeItems(): Response<HomeResponse>
}