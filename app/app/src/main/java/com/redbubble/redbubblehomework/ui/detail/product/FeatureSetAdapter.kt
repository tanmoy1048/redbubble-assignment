package com.redbubble.redbubblehomework.ui.detail.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.data.model.HomeItem
import com.redbubble.redbubblehomework.databinding.ItemFeatureSetBinding
import com.redbubble.redbubblehomework.databinding.ItemProductBinding
import javax.inject.Inject

class FeatureSetAdapter @Inject constructor() :
    RecyclerView.Adapter<FeatureSetAdapter.ViewHolder>() {
    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFeatureSetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateData(items: List<String>) {
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemFeatureSetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            with(item) {
                binding.item = this
                binding.executePendingBindings()
            }
        }
    }
}