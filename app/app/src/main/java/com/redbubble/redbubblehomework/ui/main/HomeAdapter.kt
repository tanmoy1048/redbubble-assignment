package com.redbubble.redbubblehomework.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.data.model.HomeItem

/*
class HomeAdapter(private val list: List<HomeItem>, val glide: RequestManager) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
//            holder.binding.title1.text = it.title
//            holder.binding.title2.text = if (it.type == "PRODUCT") {
//                "${it.currency} ${it.amount}"
//            } else {
//                "by ${it.artist}"
//            }
//            glide.load(it.thumbnailUrl)
//                .centerCrop()
//                .into(holder.binding.image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}*/
