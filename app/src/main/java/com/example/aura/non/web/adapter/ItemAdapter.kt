package com.example.aura.non.web.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aura.non.web.database.ItemEntity
import com.example.aura.non.web.databinding.ItemMessageBinding

class ItemAdapter : PagingDataAdapter<ItemEntity, ItemAdapter.ItemViewHolder>(ItemDiffCallback.instance) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMessageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ItemViewHolder(
        private val binding: ItemMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemEntity) {
            binding.textView.text = item.name
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }
}