package com.redbubble.redbubblehomework.data.remote

import com.redbubble.redbubblehomework.data.model.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.ResponseBody

import android.R.id
import com.redbubble.redbubblehomework.data.model.ItemDetailResponse
import retrofit2.Call


interface NetworkServiceApi {
    @GET("explore.json")
    suspend fun getHomeItems(): Response<HomeResponse>

    @GET("workDetails/{id}.json")
    suspend fun getItemDetail(@Path("id") id: String): Response<ItemDetailResponse>
}