package com.redbubble.redbubblehomework.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.redbubble.redbubblehomework.data.model.HomeItem
import com.redbubble.redbubblehomework.databinding.ItemProductBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val list = mutableListOf<HomeItem>()
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun updateData(items: List<HomeItem>) {
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { click ->
                click(list[position].id)
            }
        }
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItem) {
            with(item) {
                binding.item = this
                binding.glide = glide
                binding.executePendingBindings()
            }
        }
    }
}