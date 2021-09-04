package com.redbubble.redbubblehomework.data.model

import com.squareup.moshi.Json

data class HomeItem(
    val artist: String,
    val id: String,
    val price: Price,
    val safeForWork: Boolean,
    val thumbnailUrl: String,
    val title: String,
    val type: ItemType
)

enum class ItemType{
    @Json(name = "WORK") WORK,
    @Json(name = "PRODUCT") PRODUCT,
}