package com.redbubble.redbubblehomework.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.utils.ItemOffsetDecoration

@BindingAdapter("glide", "thumbImageUrl")
fun setThumbNailImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .centerCrop()
        .into(imageView)
}

@BindingAdapter("glide", "imageUrl")
fun setFullImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .into(imageView)
}

@BindingAdapter("glide", "circleImageUrl")
fun setCircleImageUrl(imageView: ImageView, glide: RequestManager, url: String?) {
    glide.load(url)
        .circleCrop()
        .into(imageView);
}

@BindingAdapter("gridSpace")
fun setSpaceItemDecoration(recyclerView: RecyclerView, space: Float) {
    val itemDecoration = ItemOffsetDecoration(space.toInt())
    recyclerView.addItemDecoration(itemDecoration)
}