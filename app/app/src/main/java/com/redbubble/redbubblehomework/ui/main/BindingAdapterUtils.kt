package com.redbubble.redbubblehomework.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.RequestManager

@BindingAdapter("glide", "imageUrl")
fun setImageUrl(imageView: ImageView, glide: RequestManager, url: String) {
    glide.load(url)
        .centerCrop()
        .into(imageView)
}