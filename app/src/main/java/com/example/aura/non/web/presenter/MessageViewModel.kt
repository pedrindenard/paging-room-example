package com.example.aura.non.web.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.aura.non.web.data.ItemPagingSource
import com.example.aura.non.web.database.ItemDao

class MessageViewModel(
    private val dao: ItemDao
) : ViewModel() {

    private val configs = PagingConfig(
        enablePlaceholders = false,
        initialLoadSize = 20,
        pageSize = 20
    )

    val messages = Pager(configs) { ItemPagingSource(dao) }.flow.cachedIn(viewModelScope)

}
