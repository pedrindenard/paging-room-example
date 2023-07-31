package com.example.aura.non.web.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aura.non.web.database.ItemDao

@Suppress(names = ["UNCHECKED_CAST"])
class MessageViewModelFactory(
    private val dao: ItemDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(dao) as T
        }

        throw IllegalArgumentException("Unknown View Model class")
    }
}