package com.example.aura.non.web.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.aura.non.web.adapter.ItemAdapter
import com.example.aura.non.web.adapter.ItemLoadStateAdapter
import com.example.aura.non.web.database.ItemDatabase
import com.example.aura.non.web.databinding.ActivityMessageBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MessageActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMessageBinding.inflate(layoutInflater) }
    private val dao by lazy { ItemDatabase.getInstance(this).itemDao() }

    private val viewModel by viewModels<MessageViewModel> { MessageViewModelFactory(dao) }

    private val itemAdapter by lazy { ItemAdapter() }
    private val itemStateAdapter by lazy { ItemLoadStateAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setDataObserver()
        setAdapter()
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = itemAdapter.withLoadStateFooter(itemStateAdapter)
    }

    private fun setDataObserver() = lifecycleScope.launch {
        viewModel.messages.collectLatest {
            itemAdapter.submitData(it)
        }
    }
}