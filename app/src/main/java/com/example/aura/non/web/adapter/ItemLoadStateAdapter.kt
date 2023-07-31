package com.example.aura.non.web.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aura.non.web.databinding.LoadingMessageBinding

class ItemLoadStateAdapter : LoadStateAdapter<ItemLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadingMessageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class LoadStateViewHolder(
        private val binding: LoadingMessageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progress.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}