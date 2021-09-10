package com.redbubble.redbubblehomework.data.model

data class WorkDetails(
    val artist: Artist,
    val availableProducts: List<HomeItem>,
    val id: String,
    val imageUrl: String,
    val safeForWork: Boolean,
    val shareUrl: String,
    val title: String,
    val featureSet: FeatureSet?
)