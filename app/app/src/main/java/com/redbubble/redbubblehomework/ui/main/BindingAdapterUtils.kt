package com.redbubble.redbubblehomework.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager

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