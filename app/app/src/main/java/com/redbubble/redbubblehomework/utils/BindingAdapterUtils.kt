package com.redbubble.redbubblehomework.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("glide", "thumbImageUrl")
fun setThumbNailImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("glide", "imageUrl")
fun setFullImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("glide", "circleImageUrl")
fun setCircleImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView);
}

@BindingAdapter("gridSpace")
fun setSpaceItemDecoration(recyclerView: RecyclerView, space: Float) {
    val itemDecoration = ItemOffsetDecoration(space.toInt())
    recyclerView.addItemDecoration(itemDecoration)
}