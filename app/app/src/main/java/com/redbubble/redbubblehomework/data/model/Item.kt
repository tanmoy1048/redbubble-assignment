package com.redbubble.redbubblehomework.data.model

data class Item(
    val artist: String,
    val id: String,
    val price: Price,
    val safeForWork: Boolean,
    val thumbnailUrl: String,
    val title: String,
    val type: String
)