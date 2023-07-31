package com.example.aura.non.web.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.aura.non.web.database.ItemEntity

class ItemDiffCallback : DiffUtil.ItemCallback<ItemEntity>() {

    override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem == newItem
    }

    companion object {

        val instance = ItemDiffCallback()

    }
}